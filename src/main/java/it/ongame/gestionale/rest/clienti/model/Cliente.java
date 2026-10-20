package it.ongame.gestionale.rest.clienti.model;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import it.ongame.gestionale.rest.sede.Sede;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "clienti",
        schema = "public"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SoftDelete
public class Cliente extends OnGameCommonModel {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cognome", length = 100, nullable = false)
    private String cognome;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "telefono_cifrato", columnDefinition = "text")
    private String telefonoCifrato;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sedeCliente;
}
