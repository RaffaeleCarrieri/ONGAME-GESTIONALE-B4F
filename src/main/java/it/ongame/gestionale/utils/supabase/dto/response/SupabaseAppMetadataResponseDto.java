package it.ongame.gestionale.utils.supabase.dto.response;


import it.ongame.gestionale.rest.utente.model.RuoloEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupabaseAppMetadataResponseDto {

    private String provider;
    private List<String> providers;
    private RuoloEnum role;
}
