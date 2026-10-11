package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
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
public class SedeResponseListDto extends OnGameCommonListResponseDto<SedeResponseDto> {

}
