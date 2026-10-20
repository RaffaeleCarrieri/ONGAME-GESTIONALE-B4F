package it.ongame.gestionale.utils.supabase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupabaseLoginResponseDto {

    private String access_token;
    private String token_type;
    private Long expires_in;
    private Long expires_at;
    private String refresh_token;
    private SupabaseAuthUserResponseDto user;
    private Object weak_password;
}
