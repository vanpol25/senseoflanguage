package com.senseoflanguage.dto.request;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DefinitionRequest implements Serializable {

    private String definition;
    private String partOfSpeech;
    private List<String> synonyms;
    private List<String> typeOf;
    private List<String> hasTypes;
    private List<String> examples;

}
