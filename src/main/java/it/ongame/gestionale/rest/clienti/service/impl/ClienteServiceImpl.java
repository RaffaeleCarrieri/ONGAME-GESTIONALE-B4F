package it.ongame.gestionale.rest.clienti.service.impl;

import it.ongame.gestionale.common.service.impl.OnGameCommonServiceImpl;
import it.ongame.gestionale.rest.clienti.model.Cliente;
import it.ongame.gestionale.rest.clienti.model.ClienteRepository;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteRequestDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClienteResponseDto;
import it.ongame.gestionale.rest.clienti.model.dto.ClientiResponseListDto;
import it.ongame.gestionale.rest.clienti.service.ClienteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class ClienteServiceImpl extends OnGameCommonServiceImpl<Cliente, ClienteRepository, ClienteRequestDto, ClienteResponseDto, ClientiResponseListDto> implements ClienteService {

    public ClienteServiceImpl(ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente toModel(ClienteRequestDto res) {
        return Cliente
                .builder()
                .email(res.getEmail())
                .nome(res.getNome())
                .cognome(res.getCognome())
                .telefonoCifrato(res.getTelefono())
                .dataNascita(res.getDataNascita())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public ClienteResponseDto toDto(Cliente entity) {
        return ClienteResponseDto
                .builder()
                .nome(entity.getNome())
                .cognome(entity.getCognome())
                .email(entity.getEmail())
                .dataNascita(entity.getDataNascita())
                .telefono(entity.getTelefonoCifrato())
                .build();
    }
}
