package it.ongame.gestionale.rest.utente.service;

import it.ongame.gestionale.common.service.OnGameCommonService;
import it.ongame.gestionale.rest.utente.model.RuoloEnum;
import it.ongame.gestionale.rest.utente.model.dto.UtenteRequestDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;

public interface UtenteService extends OnGameCommonService<
        UtenteRequestDto, UtenteResponseDto, UtenteResponseListDto> {

    UtenteResponseListDto findOnlyClients(Integer page,Integer elements,String sedeId);
}
