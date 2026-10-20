package it.ongame.gestionale.rest.session.model;

import it.ongame.gestionale.rest.utente.model.RuoloEnum;

import java.util.UUID;

public class SessionDto{

    public record SessionInDto(
            String name,
            String surname,
            RuoloEnum ruolo
    ){

    }

    public record FetchContextInDto(
            UUID sessionId,
            String ctxKey
    ){

    }
}
