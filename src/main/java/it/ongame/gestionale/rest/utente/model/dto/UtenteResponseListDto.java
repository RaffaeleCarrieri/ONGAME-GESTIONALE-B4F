package it.ongame.gestionale.rest.utente.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UtenteResponseListDto extends OnGameCommonListResponseDto<UtenteResponseDto> {

    public UtenteResponseListDto(Integer page, Integer elements, List<UtenteResponseDto> records) {
        super(page, elements, records);
    }
}
