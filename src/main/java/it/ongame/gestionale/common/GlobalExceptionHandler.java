package it.ongame.gestionale.common;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validazione(MethodArgumentNotValidException ex) {
        Map<String, String> campi = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(f -> campi.put(f.getField(), String.valueOf(f.getDefaultMessage())));
        return ResponseEntity.badRequest().body(Map.of("errore", "Validazione fallita", "campi", campi));
    }

    /** Errori restituiti da Supabase Auth (credenziali errate, utente esistente, ...). */
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<Map<String, Object>> supabase(RestClientResponseException ex) {
        log.warn("Errore da servizio esterno: status={}", ex.getStatusCode().value());
        HttpStatus status = ex.getStatusCode().is4xxClientError() ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_GATEWAY;
        return ResponseEntity.status(status).body(Map.of("errore", "Operazione di autenticazione non riuscita"));
    }
}
