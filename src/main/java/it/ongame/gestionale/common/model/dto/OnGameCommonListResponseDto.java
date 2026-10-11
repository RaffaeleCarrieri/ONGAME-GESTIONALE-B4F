package it.ongame.gestionale.common.model.dto;

import lombok.*;

import java.util.List;
/*
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnGameCommonListResponseDto <T extends OnGameCommonResponseDto>{

    protected Integer page;
    protected Integer elements;
    protected List<T> records;
}
