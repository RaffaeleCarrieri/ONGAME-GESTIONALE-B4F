package it.ongame.gestionale.common.service;

import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import org.springframework.data.domain.PageRequest;

public interface OnGameCommonService<REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto, RESPLIST extends OnGameCommonListResponseDto<RES>> {

    RESPLIST findAll(PageRequest pageRequest);
    RES findById(String id);
    RES create(REQ req);
    RES update(String id,REQ req) throws Exception;
    void delete(String id);

}
