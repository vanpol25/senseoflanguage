package com.senseoflanguage.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordRequest {

    private String eng;
    private String ukr;
    private String translit;
    private BigDecimal frequency;
    private List<DefinitionRequest> results;

}
