package it.ongame.gestionale.common.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.ongame.gestionale.common.controller.OnGameCrudApiCommonController;
import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import it.ongame.gestionale.common.service.OnGameCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class OnGameCrudApiCommonControllerImpl<
        REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto,
        RESLIST extends OnGameCommonListResponseDto<RES>,
        SERVICE extends OnGameCommonService<REQ,RES,RESLIST>
        >  implements OnGameCrudApiCommonController<REQ, RES, RESLIST> {

    private final SERVICE service;

    @GetMapping
    @Override
    public ResponseEntity<RESLIST> findAll(Integer page, Integer elements) {
        return ResponseEntity.ok().body(
                this.service.findAll(PageRequest.of(page, elements))
        );
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RES> findById(@PathVariable("id") String id) {

        return ResponseEntity.ok().body(
                this.service.findById(id)
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<RES> create(@RequestBody REQ req) {
        return ResponseEntity.ok().body(this.service.create(req));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }


}
