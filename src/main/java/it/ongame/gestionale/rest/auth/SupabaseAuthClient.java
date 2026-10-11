package it.ongame.gestionale.rest.auth;

import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SupabaseAuthClient {

    private final RestClient client;
    private final String anonKey;
    private final String serviceKey;

    public SupabaseAuthClient(RestClient.Builder builder,
                              @Value("${app.supabase.url}") String url,
                              @Value("${app.supabase.anon-key}") String anonKey,
                              @Value("${app.supabase.service-role-key}") String serviceKey) {
        this.client = builder.baseUrl(url).build();
        this.anonKey = anonKey;
        this.serviceKey = serviceKey;
    }

    public Map<String, Object> login(String email, String password) {
        return client.post()
                .uri("/auth/v1/token?grant_type=password")
                .header("apikey", anonKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("email", email, "password", password))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
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
