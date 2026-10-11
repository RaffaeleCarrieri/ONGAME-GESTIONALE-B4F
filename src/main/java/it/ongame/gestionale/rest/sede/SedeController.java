package it.ongame.gestionale.rest.sede;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.ongame.gestionale.common.controller.impl.OnGameCrudApiCommonControllerImpl;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** CRUD di riferimento: replica lo stesso schema per utenti, clienti, offerte... */
@RestController
@RequestMapping("/api/sedi")
@SecurityRequirement(name = "bearerAuth")
public class SedeController extends OnGameCrudApiCommonControllerImpl<
        SedeRequestDto,SedeResponseDto,SedeResponseListDto,SedeService
        > {

    public SedeController(SedeService service) {
        super(service);
    }
/*
    private final SedeService service;

    @GetMapping
    //@PreAuthorize("hasAnyRole('PROPRIETARIO','DIPENDENTE')")
    public List<SedeDto> elenco() {
        return service.elenco();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('PROPRIETARIO','DIPENDENTE')")
    public SedeDto dettaglio(@PathVariable UUID id) {
        return service.dettaglio(id);
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('PROPRIETARIO')")
    public SedeDto crea(@Valid @RequestBody SedeDto dto) {
        return service.crea(dto);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('PROPRIETARIO')")
    public SedeDto aggiorna(@PathVariable UUID id, @Valid @RequestBody SedeDto dto) {
        return service.aggiorna(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('PROPRIETARIO')")
    public void disattiva(@PathVariable UUID id) {
        service.disattiva(id);
    }*/
}
