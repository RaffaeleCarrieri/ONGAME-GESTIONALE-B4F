package it.ongame.gestionale.rest.utente.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import it.ongame.gestionale.common.repository.OnGameCommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends OnGameCommonRepository<Utente> {

    List<Utente> findByDataNascita(LocalDate oggi);
}
