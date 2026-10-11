package it.ongame.gestionale.rest.utente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.ongame.gestionale.common.controller.OnGameCrudApiCommonController; // adatta il package
import it.ongame.gestionale.rest.utente.model.dto.UtenteRequestDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UtenteApi extends
        OnGameCrudApiCommonController<UtenteRequestDto, UtenteResponseDto, UtenteResponseListDto> {

    @Override
    @Operation(summary = "Elenco paginato degli utenti",
            description = "Restituisce gli utenti non eliminati. Quelli con soft delete non sono inclusi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Elenco restituito correttamente",
                    content = @Content(schema = @Schema(implementation = UtenteResponseListDto.class))),
            @ApiResponse(responseCode = "400", description = "Parametri di paginazione non validi", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content)
    })
    ResponseEntity<UtenteResponseListDto> findAll(
            @Parameter(description = "Numero di pagina, parte da 0", example = "0",
                    schema = @Schema(minimum = "0", defaultValue = "0"))
            @RequestParam(name = "page", defaultValue = "0") Integer page,

            @Parameter(description = "Numero di elementi per pagina", example = "20",
                    schema = @Schema(minimum = "1", maximum = "100", defaultValue = "20"))
            @RequestParam(name = "elements", defaultValue = "20") Integer elements);


    @Override
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
    ResponseEntity<UtenteResponseDto> findById(
            @Parameter(description = "Id dell'utente (UUID)", example = "db21fdb9-384f-4695-bd77-12b201555a9e")
            @PathVariable(name = "id") String id);


    @Override
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
    ResponseEntity<UtenteResponseDto> create(@RequestBody UtenteRequestDto req);


    // Non presente nell'interfaccia generica
    @Operation(summary = "Aggiorna un utente",
            description = "Aggiorna nome, cognome, ruolo e stato dell'utente. L'email non è modificabile da qui.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utente aggiornato",
                    content = @Content(schema = @Schema(implementation = UtenteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Id o body non validi", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utente non trovato", content = @Content)
    })
    ResponseEntity<UtenteResponseDto> update(
            @Parameter(description = "Id dell'utente (UUID)")
            @PathVariable(name = "id") String id,
            @RequestBody UtenteRequestDto req);


    @Override
    @Operation(summary = "Elimina un utente",
            description = "Eliminazione logica (deleted = true) e blocco del login su Supabase. Il record non viene cancellato.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Utente eliminato", content = @Content),
            @ApiResponse(responseCode = "400", description = "Id non valido o auto-eliminazione", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utente non trovato", content = @Content)
    })
    void delete(
            @Parameter(description = "Id dell'utente (UUID)")
            @PathVariable(name = "id") String id);
}