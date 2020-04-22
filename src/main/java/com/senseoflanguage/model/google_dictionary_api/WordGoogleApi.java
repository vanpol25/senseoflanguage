package com.senseoflanguage.model.google_dictionary_api;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordGoogleApi implements Serializable {

    private String word;
    private String phonetic;
    private Map<String, List<DefinitionGoogleApi>> meaning = new HashMap<>();

}
