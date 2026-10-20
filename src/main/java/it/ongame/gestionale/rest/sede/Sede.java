package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import it.ongame.gestionale.rest.clienti.model.Cliente;
import it.ongame.gestionale.rest.utente.model.Utente;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sedi")
@Getter
@Setter
@SoftDelete
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

    @Builder.Default
    private boolean attiva = true;

    @Builder.Default
    @OneToMany(mappedBy = "sede")
    private List<Utente> utenti = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sedeCliente")
    private List<Cliente> clientiSede = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Sede(){}

    public Sede(UUID id, String codice, String nome, String indirizzo, String citta, String cap, String provincia, String telefono, String email, boolean aderisceApp, LocalDate dataAdesione, boolean attiva, List<Utente> utenti, List<Cliente> clientiSede, Instant createdAt, Instant updatedAt) {
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
        this.utenti = utenti;
        this.clientiSede = clientiSede;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    private void generateCode() {
        String info = String.format("%s-%s-%s", this.provincia, this.citta, this.cap);
        this.codice = "ONG" + info.hashCode();
    }
}