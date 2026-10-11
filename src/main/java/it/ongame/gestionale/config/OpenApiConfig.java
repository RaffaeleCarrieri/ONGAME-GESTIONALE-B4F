package it.ongame.gestionale.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import it.ongame.gestionale.common.controller.OnGameCrudApiCommonController;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Ongame Gestionale API", version = "v1"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
        scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {

    private final Set<Class<?>> dtoUsati = ConcurrentHashMap.newKeySet();

    @Bean
    public OperationCustomizer controllerTagCustomizer() {
        return (operation, handlerMethod) -> {
            String tagName = getTagName(handlerMethod);

            // Sostituisce i tag esistenti invece di aggiungerne uno nuovo
            operation.setTags(List.of(tagName));

            return operation;
        };
    }

    /*@Bean
    OperationCustomizer schemaCrudGenerico() {
        return (operation, handlerMethod) -> {
            Class<?> controller = handlerMethod.getBeanType();
            if (!OnGameCrudApiCommonController.class.isAssignableFrom(controller)
                    || operation.getResponses() == null) {
                return operation;
            }

            // Tipo di ritorno con i generics risolti sulla classe concreta: ResponseEntity<RESLIST> -> ResponseEntity<UtenteResponseListDto>
            Class<?> dto = ResolvableType
                    .forMethodReturnType(handlerMethod.getMethod(), controller)
                    .as(ResponseEntity.class)
                    .getGeneric(0)
                    .resolve();
            if (dto == null || dto == Object.class) {
                return operation;   // es. void (delete)
            }

            operation.getResponses().forEach((codice, risposta) -> {
                if (codice.startsWith("2") && !codice.equals("204")) {
                    impostaSchema(risposta, dto);
                }
            });
            dtoUsati.add(dto);
            return operation;
        };
    }

    *//** Registra in components/schemas i DTO usati, compresi quelli annidati, così i $ref si risolvono. *//*
    @Bean
    OpenApiCustomizer registraSchemiCrud() {
        return openApi -> {
            if (openApi.getComponents() == null) {
                openApi.setComponents(new Components());
            }
            Components components = openApi.getComponents();
            for (Class<?> dto : dtoUsati) {
                ModelConverters.getInstance().readAll(dto).forEach((nome, schema) -> {
                    if (components.getSchemas() == null || !components.getSchemas().containsKey(nome)) {
                        components.addSchemas(nome, schema);
                    }
                });
            }
        };
    }

    private void impostaSchema(ApiResponse risposta, Class<?> dto) {
        Schema<?> ref = new Schema<>().$ref("#/components/schemas/" + dto.getSimpleName());
        risposta.setContent(new Content().addMediaType("application/json", new MediaType().schema(ref)));
    }
*/
    private String getTagName(HandlerMethod handlerMethod) {
        String className = handlerMethod.getBeanType().getSimpleName();

        return className.replaceFirst("Controller$", "");
    }

}
