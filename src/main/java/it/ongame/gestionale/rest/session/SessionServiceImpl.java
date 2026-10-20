package it.ongame.gestionale.rest.session;

import it.ongame.gestionale.rest.session.model.Session;
import it.ongame.gestionale.rest.session.model.SessionDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService{

    @Override
    public Session getSession(UUID sessionId) {
        return null;
    }

    @Override
    public void insertSession(SessionDto.SessionInDto session) {

    }

    @Override
    public Object getFromContext(SessionDto.FetchContextInDto cachKey) {
        return null;
    }

    @Override
    public Object removeFromContext(SessionDto.FetchContextInDto cachKey) {
        return null;
    }

    @Override
    public Object addOrUpdateIntoContext(SessionDto.FetchContextInDto cachKey) {
        return null;
    }
}
