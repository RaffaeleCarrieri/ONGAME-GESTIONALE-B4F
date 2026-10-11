package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SedeRequestDto extends OnGameCommonRequestDto {
    @NotBlank @Size(max = 20) String codice;
    @NotBlank @Size(max = 150) String nome;
    String indirizzo;
    String citta;
    String cap;
    @Size(max = 2) String provincia;
    String telefono;
    @Email String email;
    Boolean aderisceApp;
    LocalDate dataAdesione;
    Boolean attiva;
}
