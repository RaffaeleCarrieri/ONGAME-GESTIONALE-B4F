package it.ongame.gestionale.rest.clienti.model.dto;

import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientiResponseListDto extends OnGameCommonListResponseDto<ClienteResponseDto> {
    public ClientiResponseListDto(Integer page, Integer elements, List<ClienteResponseDto> records) {
        super(page, elements, records);
    }
}
