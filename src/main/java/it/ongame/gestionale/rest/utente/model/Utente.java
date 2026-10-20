package it.ongame.gestionale.rest.utente.model;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import it.ongame.gestionale.rest.sede.Sede;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@SoftDelete
@Builder
public class Utente extends OnGameCommonModel {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //private UUID sedeId;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "ruolo")
    private RuoloEnum ruolo;

    //@Convert(converter = PiiConverter.class)
    @Column(name = "telefono_cifrato")
    private String telefono;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public Utente(){}

    public Utente(UUID id, String nome, String cognome, LocalDate dataNascita, String email, Sede sede, RuoloEnum ruolo, String telefono, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.sede = sede;
        this.ruolo = ruolo;
        this.telefono = telefono;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
