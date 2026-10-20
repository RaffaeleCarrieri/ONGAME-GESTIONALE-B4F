package it.ongame.gestionale.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import it.ongame.gestionale.common.controller.impl.OnGameCrudApiCommonControllerImpl;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ongame Gestionale API",
                version = "v1"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    private final String regxp = "$entity";

    /*
     * Personalizza i tag Swagger usando il nome del controller.
     */
    @Bean
    public OperationCustomizer controllerTagCustomizer() {
        return (operation, handlerMethod) -> {
            String tagName = getTagName(
                    handlerMethod.getBeanType().getSimpleName()
            );

            operation.setTags(List.of(tagName));
            operation.setOperationId(operation.getOperationId().replace(regxp, tagName));
            if(operation.getSummary()!=null) {
                operation.setSummary(operation.getSummary().replace(regxp, tagName));
            }

            if(operation.getDescription()!=null) {
                operation.setDescription(operation.getDescription().replace(regxp, tagName));
            }
            return operation;
        };
    }

    /*
     * Risolve automaticamente RESLIST dalla gerarchia generica
     * dei controller CRUD e aggiorna lo schema della risposta 200.
     */
    @Bean
    public GlobalOpenApiCustomizer genericResponseSchemaCustomizer(
            @Qualifier("requestMappingHandlerMapping")
            RequestMappingHandlerMapping handlerMapping) {

        return openApi -> {
            if (openApi.getPaths() == null) {
                return;
            }

            handlerMapping.getHandlerMethods().forEach((mapping, handlerMethod) -> {
                Class<?> controllerClass = handlerMethod.getBeanType();

                ResolvableType controllerType = ResolvableType
                        .forClass(controllerClass)
                        .as(OnGameCrudApiCommonControllerImpl.class);

                if (controllerType == ResolvableType.NONE) {
                    return;
                }

                Class<?> responseListClass =
                        controllerType.getGeneric(2).resolve();

                if (responseListClass == null) {
                    return;
                }

                mapping.getPatternValues().forEach(path -> {
                    PathItem pathItem = openApi.getPaths().get(path);

                    if (pathItem == null) {
                        return;
                    }

                    for (RequestMethod method :
                            mapping.getMethodsCondition().getMethods()) {

                        Operation operation = getOperation(pathItem, method);
                        if (operation == null || operation.getResponses() == null) {
                            continue;
                        }

                        ApiResponse response =
                                operation.getResponses().get("200");

                        if (response == null || response.getContent() == null) {
                            continue;
                        }

                        Schema<?> schema = new Schema<>()
                                .$ref("#/components/schemas/"
                                        + responseListClass.getSimpleName());

                        response.getContent().values().forEach(mediaType ->
                                mediaType.setSchema(schema)
                        );
                    }
                });
            });
        };
    }

    /*
     * Recupera l'operazione OpenAPI corrispondente al metodo HTTP.
     */
    private Operation getOperation(
            PathItem pathItem,
            RequestMethod method) {

        return switch (method) {
            case GET -> pathItem.getGet();
            case POST -> pathItem.getPost();
            case PUT -> pathItem.getPut();
            case PATCH -> pathItem.getPatch();
            case DELETE -> pathItem.getDelete();
            default -> null;
        };
    }

    /*
     * Rimuove il suffisso Controller dal nome della classe
     * per ottenere il tag Swagger.
     */
    private String getTagName(String className) {
        return className.replaceFirst("Controller$", "");
    }
}