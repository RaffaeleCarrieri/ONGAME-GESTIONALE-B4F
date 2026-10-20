package it.ongame.gestionale.rest.session;

import it.ongame.gestionale.rest.session.model.Session;
import it.ongame.gestionale.rest.session.model.SessionDto;

import java.util.UUID;

public interface SessionService {


    Session getSession(UUID sessionId);

    void insertSession(SessionDto.SessionInDto session);

    Object getFromContext(SessionDto.FetchContextInDto cachKey);

    Object removeFromContext(SessionDto.FetchContextInDto cachKey);

    Object addOrUpdateIntoContext(SessionDto.FetchContextInDto cachKey);


}
