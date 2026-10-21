package it.ongame.gestionale.common.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.ongame.gestionale.common.controller.OnGameCrudApiCommonController;
import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import it.ongame.gestionale.common.service.OnGameCommonService;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class OnGameCrudApiCommonControllerImpl<
        REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto,
        RESLIST extends OnGameCommonListResponseDto<RES>,
        SERVICE extends OnGameCommonService<REQ,RES,RESLIST>
        >  implements OnGameCrudApiCommonController<REQ, RES, RESLIST> {

    private final SERVICE service;

    @GetMapping
    @Override
    @Operation(
            operationId = "get_$entity_list",
            summary = "Elenco paginato della entity $entity",
            description = "Restituisce $entity non eliminati. Quelli con soft delete non sono inclusi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Elenco restituito correttamente",
                    content = @Content(schema = @Schema(implementation = UtenteResponseListDto.class))),
            @ApiResponse(responseCode = "400", description = "Parametri di paginazione non validi", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content)
    })
    public ResponseEntity<RESLIST> findAll(Integer page, Integer elements) {
        return ResponseEntity.ok().body(
                this.service.findAll(PageRequest.of(page, elements))
        );
    }

    @GetMapping("/{id}")
    @Override
    @Operation(
            operationId = "get_$entity_by_id",
            summary = "Trova un elemento $entity per id",
            description = "Trova un elemento $entity per id (UUID dell'account Supabase).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utente trovato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Id non valido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utente non trovato", content = @Content)
    })
    public ResponseEntity<RES> findById(@PathVariable("id") String id) {

        return ResponseEntity.ok().body(
                this.service.findById(id)
        );
    }

    @Override
    @PostMapping
    @Operation(
            operationId = "create_$entity",
            summary = "Crea $entity",
            description = "Crea $entity")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "$entity creato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Body non valido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email già registrata", content = @Content)
    })
    public ResponseEntity<RES> create(@RequestBody @Valid REQ req) {
        return ResponseEntity.ok().body(this.service.create(req));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
            operationId = "delete_$entity",
            summary = "Elimina $entity dal suo id",
            description = "Elimina $entity dal suo id")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "$entity creato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Body non valido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email già registrata", content = @Content)
    })
    public void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }


}
