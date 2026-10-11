package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SedeResponseDto extends OnGameCommonResponseDto {
    UUID id;
    String codice;
    String nome;
    String indirizzo;
    String citta;
    String cap;
    String provincia;
    String telefono;
    String email;
    Boolean aderisceApp;
    LocalDate dataAdesione;
    Boolean attiva;
}
