package it.ongame.gestionale.rest.auth.model.dto;

import it.ongame.gestionale.common.model.dto.CommonEsitoDto;
import it.ongame.gestionale.rest.utente.model.RuoloEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponseDto {
    UUID userId;
    String name;
    String surname;
    RuoloEnum ruolo;
    TokenInfoDto tokenInfo;
    CommonEsitoDto esito;

}
