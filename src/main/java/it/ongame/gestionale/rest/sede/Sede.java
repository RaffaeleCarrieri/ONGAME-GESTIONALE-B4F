package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sedi")
@Getter
@Setter
@Builder
public class Sede extends OnGameCommonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String codice;
    private String nome;
    private String indirizzo;
    private String citta;
    private String cap;
    private String provincia;
    private String telefono;
    private String email;
    private boolean aderisceApp;
    private LocalDate dataAdesione;
    private boolean attiva = true;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Sede(){}

    public Sede(UUID id, String codice, String nome, String indirizzo, String citta, String cap, String provincia, String telefono, String email, boolean aderisceApp, LocalDate dataAdesione, boolean attiva, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.codice = codice;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.aderisceApp = aderisceApp;
        this.dataAdesione = dataAdesione;
        this.attiva = attiva;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
