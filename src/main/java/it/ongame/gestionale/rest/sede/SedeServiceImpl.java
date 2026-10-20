package it.ongame.gestionale.rest.sede;

import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import org.springframework.stereotype.Service;

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
                //.codice(res.getCodice())
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
}
