package it.ongame.gestionale.rest.clienti.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDto extends OnGameCommonResponseDto {
    private String id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String sedeId;
    private String email;
    private String telefono;
}
