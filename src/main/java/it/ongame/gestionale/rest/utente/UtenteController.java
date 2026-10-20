package it.ongame.gestionale.rest.utente;

import it.ongame.gestionale.common.controller.impl.OnGameCrudApiCommonControllerImpl;
import it.ongame.gestionale.rest.utente.model.dto.UtenteRequestDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;
import it.ongame.gestionale.rest.utente.service.UtenteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/utenti")
public class UtenteController extends OnGameCrudApiCommonControllerImpl<
        UtenteRequestDto,
        UtenteResponseDto,
        UtenteResponseListDto,
        UtenteService> {

    public UtenteController(UtenteService service) {
        super(service);
    }
    //private final UtenteService utenteService;

    /*@GetMapping
    @Operation(
            summary = "Elenco paginato degli utenti",
            description = "Restituisce gli utenti non eliminati. Gli utenti con soft delete non sono inclusi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Elenco restituito correttamente",
                    content = @Content(schema = @Schema(implementation = UtenteResponseListDto.class))),
            @ApiResponse(responseCode = "400", description = "Parametri di paginazione non validi",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato",
                    content = @Content)
    })
    public ResponseEntity<UtenteResponseListDto> getUtenti(
            @Parameter(description = "Numero di pagina, parte da 0", example = "0",
                    schema = @Schema(minimum = "0", defaultValue = "0"))
            @RequestParam(name = "page", defaultValue = "0") Integer page,

            @Parameter(description = "Numero di elementi per pagina", example = "20",
                    schema = @Schema(minimum = "1", maximum = "100", defaultValue = "20"))
            @RequestParam(name = "elements", defaultValue = "20") Integer elements) {

        return ResponseEntity.ok().body(
                this.utenteService.findAll(PageRequest.of(page, elements))
        );
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Elenco paginato degli utenti",
            description = "Restituisce gli utenti non eliminati. Gli utenti con soft delete non sono inclusi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Elenco restituito correttamente",
                    content = @Content(schema = @Schema(implementation = UtenteResponseListDto.class))),
            @ApiResponse(responseCode = "400", description = "Parametri di paginazione non validi",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Token mancante o non valido",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ruolo non autorizzato",
                    content = @Content)
    })
    public ResponseEntity<UtenteResponseDto> getUtente(
            @Parameter(description = "id utente")
            @PathVariable(name = "id") String id){

        return ResponseEntity.ok().body(
                this.utenteService.findById(id)
        );
    }
*/


}
