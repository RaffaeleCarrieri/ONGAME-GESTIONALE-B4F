package it.ongame.gestionale.rest.auth;

import it.ongame.gestionale.utils.supabase.dto.request.SupabaseLoginRequestDto;
import it.ongame.gestionale.utils.supabase.dto.response.SupabaseLoginResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;
import java.util.UUID;

@Component
public class SupabaseAuthClient {

    private final RestClient client;
    private final String anonKey;
    private final String serviceKey;

    private static final RestClient.ResponseSpec.ErrorHandler NON_2XX = (req, res) -> {
        throw new RuntimeException("Errore Supabase: HTTP " + res.getStatusCode());
    };

    public SupabaseAuthClient(RestClient.Builder builder,
                              @Value("${app.supabase.url}") String url,
                              @Value("${app.supabase.anon-key}") String anonKey,
                              @Value("${app.supabase.service-role-key}") String serviceKey) {
        this.client = builder.baseUrl(url).build();
        this.anonKey = anonKey;
        this.serviceKey = serviceKey;
    }

    public SupabaseLoginResponseDto login(String email, String password)
            throws Exception {

        try {
            return client.post()
                    .uri("/auth/v1/token?grant_type=password")
                    .header("apikey", anonKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(SupabaseLoginRequestDto.builder()
                            .email(email)
                            .password(password)
                            .build())
                    .retrieve()
                    .body(SupabaseLoginResponseDto.class);

        } catch (RestClientResponseException e) {
            throw new Exception(
                    "Errore Supabase: HTTP " + e.getStatusCode().value()
                            + " - " + e.getResponseBodyAsString(),
                    e
            );
        }
    }

    public UUID creaUtente(String email, String password, String ruolo) {
        Map<String, Object> res = client.post()
                .uri("/auth/v1/admin/users")
                .header("apikey", serviceKey)
                .header("Authorization", "Bearer " + serviceKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("email", email, "password", password,
                        "email_confirm", true,
                        "app_metadata", Map.of("role", ruolo)))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return UUID.fromString(res.get("id").toString());
    }
}
