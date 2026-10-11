package it.ongame.gestionale.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface OnGameCrudApiCommonController<
        REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto,
        RESLIST extends OnGameCommonListResponseDto<RES>> {

    @Operation(summary = "Elenco paginato degli utenti",
            description = "Restituisce gli utenti non eliminati. Quelli con soft delete non sono inclusi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Elenco restituito correttamente",
                    content = @Content(schema = @Schema(implementation = UtenteResponseListDto.class))),
            @ApiResponse(responseCode = "400", description = "Parametri di paginazione non validi", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content)
    })
     ResponseEntity<RESLIST> findAll(
            @Parameter(description = "Numero di pagina, parte da 0", example = "0",
                    schema = @Schema(minimum = "0", defaultValue = "0"))
            @RequestParam(name = "page", defaultValue = "0") Integer page,

            @Parameter(description = "Numero di elementi per pagina", example = "20",
                    schema = @Schema(minimum = "1", maximum = "100", defaultValue = "20"))
            @RequestParam(name = "elements", defaultValue = "20") Integer elements);


    @Operation(summary = "Trova un utente per id",
            description = "Restituisce l'utente con l'id indicato (UUID dell'account Supabase).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utente trovato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Id non valido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utente non trovato", content = @Content)
    })
    ResponseEntity<RES> findById(
            @Parameter(description = "id utente")
            @PathVariable(name = "id") String id
    );

    @Operation(summary = "Crea un utente",
            description = "Crea il login su Supabase Auth e il profilo nella tabella utenti.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utente creato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Body non valido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email già registrata", content = @Content)
    })
    ResponseEntity<RES> create(REQ req);

    void delete(String id);
}
