package it.ongame.gestionale.rest.utente.model;

import it.ongame.gestionale.common.model.OnGameCommonModel;
//import it.ongame.gestionale.common.PiiConverter;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

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
    //@Convert(converter = PiiConverter.class)
    @Column(name = "telefono_cifrato")
    private String telefono;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public Utente(){}

    public Utente(UUID id, String nome, String cognome, LocalDate dataNascita, String email, String telefono, Instant createdAt, Instant updatedAt) {
        this.id = id;
        //this.sedeId = sedeId;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.telefono = telefono;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
