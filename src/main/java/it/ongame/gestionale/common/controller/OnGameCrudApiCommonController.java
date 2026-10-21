package it.ongame.gestionale.common.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface OnGameCrudApiCommonController<
        REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto,
        RESLIST extends OnGameCommonListResponseDto<RES>> {

     ResponseEntity<RESLIST> findAll(
            @Parameter(description = "Numero di pagina, parte da 0", example = "0",
                    schema = @Schema(minimum = "0", defaultValue = "0"))
            @RequestParam(name = "page", defaultValue = "0") Integer page,

            @Parameter(description = "Numero di elementi per pagina", example = "20",
                    schema = @Schema(minimum = "1", maximum = "100", defaultValue = "20"))
            @RequestParam(name = "elements", defaultValue = "20") Integer elements);


    ResponseEntity<RES> findById(
            @Parameter(description = "id utente")
            @PathVariable(name = "id") String id
    );

    ResponseEntity<RES> create(REQ req);

    ResponseEntity<RES> update(String id,REQ req) throws Exception;

    void delete(String id);
}
