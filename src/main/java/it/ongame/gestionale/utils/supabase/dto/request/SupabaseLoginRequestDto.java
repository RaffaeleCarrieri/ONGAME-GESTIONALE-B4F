package it.ongame.gestionale.utils.supabase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupabaseLoginRequestDto {
    private String email;
    private String password;
}
