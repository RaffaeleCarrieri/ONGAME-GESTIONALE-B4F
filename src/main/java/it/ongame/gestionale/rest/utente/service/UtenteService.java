package it.ongame.gestionale.rest.utente.service;

import it.ongame.gestionale.common.service.OnGameCommonService;
import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import it.ongame.gestionale.rest.utente.model.Utente;
import it.ongame.gestionale.rest.utente.model.UtenteRepository;
import it.ongame.gestionale.rest.utente.model.dto.UtenteRequestDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;

public interface UtenteService extends OnGameCommonService<UtenteRequestDto, UtenteResponseDto, UtenteResponseListDto> {
}
