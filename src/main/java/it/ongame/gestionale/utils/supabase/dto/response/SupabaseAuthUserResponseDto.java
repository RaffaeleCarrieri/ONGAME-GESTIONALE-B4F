package it.ongame.gestionale.utils.supabase.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupabaseAuthUserResponseDto {

    private String id;
    private String aud;
    private String role;
    private String email;
    @JsonProperty("email_confirmed_at")
    private String emailConfirmedAt;
    private String phone;

    @JsonProperty("confirmed_at")
    private String confirmedAt;

    @JsonProperty("last_sign_in_at")
    private String lastSignInAt;

    @JsonProperty("app_metadata")
    private SupabaseAppMetadataResponseDto appMetadata;

    @JsonProperty("user_metadata")
    private SupabaseUserMetadataResponseDto userMetadata;
    private List<SupabaseIdentityResponseDto> identities;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;
}
