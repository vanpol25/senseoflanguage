package com.senseoflanguage.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"definition", "partOfSpeech"})
@ToString
public class Definition implements Serializable {

    private String definition;
    private String partOfSpeech;
    private Set<String> synonyms = new HashSet<>();
    private Set<String> antonyms = new HashSet<>();
    private Set<String> typeOf = new HashSet<>();
    private Set<String> hasTypes = new HashSet<>();
    private Set<String> similarTo = new HashSet<>();
    private Set<String> examples = new HashSet<>();

}
