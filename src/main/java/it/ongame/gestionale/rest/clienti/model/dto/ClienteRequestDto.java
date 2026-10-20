package it.ongame.gestionale.rest.clienti.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequestDto extends OnGameCommonRequestDto {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String telefono;
}
