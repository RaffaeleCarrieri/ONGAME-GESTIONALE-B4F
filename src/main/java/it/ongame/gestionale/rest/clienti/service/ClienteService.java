package it.ongame.gestionale.rest.clienti.service;

import it.ongame.gestionale.common.service.OnGameCommonService;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteRequestDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteResponseDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClientiResponseListDto;

public interface ClienteService extends OnGameCommonService<ClienteRequestDto, ClienteResponseDto, ClientiResponseListDto> {
}
