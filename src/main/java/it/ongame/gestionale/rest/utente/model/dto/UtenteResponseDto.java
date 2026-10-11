package it.ongame.gestionale.rest.utente.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtenteResponseDto extends OnGameCommonResponseDto {
    private String id;
    private String sedeId;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String telefono;
    private Instant createdAt;
    private Instant updatedAt;
}
