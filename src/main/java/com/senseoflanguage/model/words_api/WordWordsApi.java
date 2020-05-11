package com.senseoflanguage.model.words_api;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordWordsApi implements Serializable {

    private String word;
    private BigDecimal frequency;
    private Set<DefinitionWordsApi> results = new HashSet<>();

}
