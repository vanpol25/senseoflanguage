package com.senseoflanguage.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DefinitionResponse implements Serializable {

    private String definition;
    private String partOfSpeech;
    private Set<String> synonyms = new HashSet<>();
    private Set<String> antonyms = new HashSet<>();
    private Set<String> typeOf = new HashSet<>();
    private Set<String> hasTypes = new HashSet<>();
    private Set<String> similarTo = new HashSet<>();
    private Set<String> examples = new HashSet<>();

}
