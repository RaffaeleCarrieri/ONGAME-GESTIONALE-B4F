package it.ongame.gestionale.rest.utente.service.impl;

import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import it.ongame.gestionale.rest.auth.SupabaseAuthClient;
import it.ongame.gestionale.rest.utente.model.RuoloEnum;
import it.ongame.gestionale.rest.utente.model.Utente;
import it.ongame.gestionale.rest.utente.model.UtenteRepository;
import it.ongame.gestionale.rest.utente.model.dto.UtenteRequestDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseDto;
import it.ongame.gestionale.rest.utente.model.dto.UtenteResponseListDto;
import it.ongame.gestionale.rest.utente.service.UtenteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtenteServiceImpl extends OnGameCommonServiceImpl<Utente, UtenteRepository, UtenteRequestDto,UtenteResponseDto, UtenteResponseListDto>
        implements UtenteService {

    private final SupabaseAuthClient supabase;

    public UtenteServiceImpl(UtenteRepository repository, SupabaseAuthClient supabase) {
        super(repository);
        this.supabase = supabase;
    }

    @Override
    public Utente toModel(UtenteRequestDto utenteRequestDto) {
        UUID id = supabase.creaUtente(utenteRequestDto.getEmail(), utenteRequestDto.getPassword(), utenteRequestDto.getRuolo().name());
        return Utente
                .builder()
                .id(id)
                .nome(utenteRequestDto.getNome())
                .cognome(utenteRequestDto.getCognome())
                .email(utenteRequestDto.getEmail())
                .dataNascita(utenteRequestDto.getDataNascita())
                //.sedeId(UUID.fromString(utenteRequestDto.getSedeId()))
                .telefono(utenteRequestDto.getTelefono())
                .createdAt(utenteRequestDto.getCreatedAt())
                .updatedAt(utenteRequestDto.getUpdatedAt())
                .build();
    }

    @Override
    public UtenteResponseDto toDto(Utente entity) {
        return UtenteResponseDto
                .builder()
                .id(entity.getId().toString())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .cognome(entity.getCognome())
                .dataNascita(entity.getDataNascita())
                .telefono(entity.getTelefono())
                .build();
    }

    @Override
    public UtenteResponseListDto findOnlyClients(Integer page, Integer elements, String sedeId) {
        return this.findAll(PageRequest.of(page,elements));
    }

    @Override
    public UtenteResponseDto mapListDtoItem(Utente utente) {
        return UtenteResponseDto
                .builder()
                .id(utente.getId().toString())
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .build();
    }
}
