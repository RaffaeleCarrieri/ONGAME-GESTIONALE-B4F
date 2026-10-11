/*
package it.ongame.gestionale.rest.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.ongame.gestionale.rest.auth.AuthDtos.LoginRequest;
import it.ongame.gestionale.rest.auth.AuthDtos.RegistrazioneRequest;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticazione")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/registrazione")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, UUID> registrazione(@Valid @RequestBody RegistrazioneRequest req) {
        return Map.of("id", authService.registraPrimoProprietario(req));
    }
}
*/
