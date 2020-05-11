package com.senseoflanguage.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordResponse {

    private String id;
    private String eng;
    private String ukr;
    private String translit;
    private BigDecimal frequency;
    private List<DefinitionResponse> results;

}
