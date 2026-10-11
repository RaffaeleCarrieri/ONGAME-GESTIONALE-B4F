package it.ongame.gestionale.rest.sede;

import java.util.UUID;

import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SedeServiceImpl extends OnGameCommonServiceImpl<
        Sede,SedeRepository,
        SedeRequestDto,
        SedeResponseDto,
        SedeResponseListDto> implements SedeService {

    public SedeServiceImpl(SedeRepository repository) {
        super(repository);
    }

    @Override
    public Sede toModel(SedeRequestDto res) {
        return Sede
                .builder()
                .codice(res.getCodice())
                .nome(res.getNome())
                .indirizzo(res.getIndirizzo())
                .citta(res.getCitta())
                .cap(res.getCap())
                .provincia(res.getProvincia())
                .telefono(res.getTelefono())
                .email(res.getEmail())
                .aderisceApp(Boolean.TRUE.equals(res.getAderisceApp()))
                .dataAdesione(res.getDataAdesione())
                .attiva(res.getAttiva() == null || res.getAttiva())
                .build();
    }

    @Override
    public SedeResponseDto toDto(Sede entity) {
        return SedeResponseDto
                .builder()
                .id(entity.getId())
                .codice(entity.getCodice())
                .nome(entity.getNome())
                .indirizzo(entity.getIndirizzo())
                .citta(entity.getCitta())
                .cap(entity.getCap())
                .provincia(entity.getProvincia())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .aderisceApp(entity.isAderisceApp())
                .dataAdesione(entity.getDataAdesione())
                .attiva(entity.isAttiva())
                .build();
    }


    //private final SedeRepository repo;

    /*@Transactional(readOnly = true)
    public List<SedeDto> elenco() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public SedeDto dettaglio(UUID id) {
        return toDto(trova(id));
    }

    public SedeDto crea(SedeDto dto) {
        if (repo.existsByCodice(dto.codice())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Codice sede gia' esistente");
        }
        Sede s = new Sede();
        applica(s, dto);
        return toDto(repo.save(s));
    }

    public SedeDto aggiorna(UUID id, SedeDto dto) {
        Sede s = trova(id);
        applica(s, dto);
        return toDto(s);
    }*/

    /** Disattivazione logica: le FK da clienti/messaggi restano valide. */
/*    public void disattiva(UUID id) {
        trova(id).setAttiva(false);
    }

    private Sede trova(UUID id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sede non trovata"));
    }

    private void applica(Sede s, SedeResponseDto d) {
        s.setCodice(d.codice());
        s.setNome(d.nome());
        s.setIndirizzo(d.indirizzo());
        s.setCitta(d.citta());
        s.setCap(d.cap());
        s.setProvincia(d.provincia());
        s.setTelefono(d.telefono());
        s.setEmail(d.email());
        if (d.aderisceApp() != null) s.setAderisceApp(d.aderisceApp());
        s.setDataAdesione(d.dataAdesione());
        if (d.attiva() != null) s.setAttiva(d.attiva());
    }

    private SedeResponseDto toDto(Sede s) {
        return new SedeResponseDto(s.getId(), s.getCodice(), s.getNome(), s.getIndirizzo(), s.getCitta(),
                s.getCap(), s.getProvincia(), s.getTelefono(), s.getEmail(),
                s.isAderisceApp(), s.getDataAdesione(), s.isAttiva());
    }*/
}
