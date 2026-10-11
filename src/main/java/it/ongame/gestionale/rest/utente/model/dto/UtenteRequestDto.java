package it.ongame.gestionale.rest.utente.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.rest.utente.model.RuoloEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteRequestDto extends OnGameCommonRequestDto {
    //private String sedeId;
    private String nome;
    private String password;
    private String cognome;
    private LocalDate dataNascita;

    @Enumerated(EnumType.STRING)
    private RuoloEnum ruolo;
    private String email;
    private String telefono;
    private Instant createdAt;
    private Instant updatedAt;
}
