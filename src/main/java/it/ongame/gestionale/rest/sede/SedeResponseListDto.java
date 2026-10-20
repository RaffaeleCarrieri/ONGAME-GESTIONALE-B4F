package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SedeResponseListDto extends OnGameCommonListResponseDto<SedeResponseDto> {

    public SedeResponseListDto(Integer page, Integer elements, List<SedeResponseDto> records) {
        super(page, elements, records);
    }
}
