package it.ongame.gestionale.rest.session.model;

import it.ongame.gestionale.rest.utente.model.RuoloEnum;

import java.util.Map;
import java.util.UUID;

public record Session(
        UUID sessionId,
        Long expiresAt,
        Long durataSecondi,
        UserContext ctx) {

    public record UserContext(
            UUID userId,
            String name,
            String surname,
            RuoloEnum ruolo,
            Map<String , Object> ctx){

    }
}
