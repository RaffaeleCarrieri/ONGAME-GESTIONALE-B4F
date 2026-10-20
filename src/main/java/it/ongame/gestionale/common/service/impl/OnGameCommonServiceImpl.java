package it.ongame.gestionale.common.service.impl;

import it.ongame.gestionale.common.model.OnGameCommonModel;
import it.ongame.gestionale.common.model.dto.OnGameCommonListResponseDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonRequestDto;
import it.ongame.gestionale.common.model.dto.OnGameCommonResponseDto;
import it.ongame.gestionale.common.repository.OnGameCommonRepository;
import it.ongame.gestionale.common.service.OnGameCommonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class OnGameCommonServiceImpl<
        E extends OnGameCommonModel,
        REPO extends OnGameCommonRepository<E>,
        REQ extends OnGameCommonRequestDto,
        RES extends OnGameCommonResponseDto,
        RESLIST extends OnGameCommonListResponseDto<RES>
        > implements OnGameCommonService<REQ,RES,RESLIST> {

    private final REPO repository;

    @Override
    public RESLIST findAll(PageRequest pageRequest) {
        try {
            List<RES> list = this.repository
                    .findAll(pageRequest)
                    .stream().map(
                            this::mapListDtoItem
                    ).toList();
            ParameterizedType type =
                    (ParameterizedType) getClass().getGenericSuperclass();

            Class<RESLIST> listRespType = (Class<RESLIST>) type.getActualTypeArguments()[4];
            return listRespType.getDeclaredConstructor(Integer.class,Integer.class,List.class)
                    .newInstance(pageRequest.getPageNumber(),pageRequest.getPageSize(),list);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RES findById(String id) {
        Optional<E> entity = this.repository.findById(UUID.fromString(id));
        return entity.map(this::toDto).orElseThrow(()->new RuntimeException(String.format("Utente %s non trovato",id)));
    }

    @Override
    public RES create(REQ req) {
        E newRecord = this.repository.saveAndFlush(toModel(req));
        return toDto(newRecord);
    }

    public  RES patch(String id,REQ req){
        E recordToUpdate = this.repository.findById(UUID.fromString(id)).orElseThrow();
        List<String> settersMethods = Arrays
                .stream(recordToUpdate.getClass().getDeclaredMethods())
                .map(Method::getName)
                .filter(
                        method->method.equalsIgnoreCase("setid") &&
                                (method.startsWith("set")||method.startsWith("is"))
                )
                .toList();

        settersMethods.forEach(
                method->{
                    //Method m =
                }
        );

        return null;

    }

    @Override
    public void delete(String id){
        this.repository.deleteById(UUID.fromString(id));
    }

    public abstract E toModel(REQ res);

    public abstract RES toDto(E entity);

    //fai l'override del metodo solo se vuoi far visualizzare alcuni campi
    public RES mapListDtoItem(E e){
        return toDto(e);
    }
}
