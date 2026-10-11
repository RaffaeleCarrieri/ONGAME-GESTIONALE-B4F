/*
package it.ongame.gestionale.rest.auth;

import it.ongame.gestionale.rest.auth.AuthDtos.LoginRequest;
import it.ongame.gestionale.rest.auth.AuthDtos.RegistrazioneRequest;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SupabaseAuthClient supabase;
    private final JdbcTemplate jdbc;

    public Map<String, Object> login(LoginRequest req) {
        return supabase.login(req.email(), req.password());
    }

    */
/**
     * Bootstrap del primo proprietario: consentito solo se la tabella utenti e' vuota.
     * Gli utenti successivi vanno creati da un PROPRIETARIO autenticato (da implementare in /api/utenti).
     *//*

    @Transactional
    public UUID registraPrimoProprietario(RegistrazioneRequest req) {
        Integer count = jdbc.queryForObject("select count(*) from utenti", Integer.class);
        if (count != null && count > 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Registrazione pubblica chiusa");
        }
        UUID id = supabase.creaUtente(req.email(), req.password(), "PROPRIETARIO");
        jdbc.update("""
                insert into utenti (id, nome, cognome, email, ruolo)
                values (?, ?, ?, ?, 'PROPRIETARIO'::ruolo_utente)
                """, id, req.nome(), req.cognome(), req.email());
        return id;
    }
}
*/
