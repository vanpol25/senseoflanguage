package com.senseoflanguage.dto.request;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordRequest {

    private String eng;
    private String ukr;
    private String translit;
    private Set<DefinitionRequest> results = new HashSet<>();

}
