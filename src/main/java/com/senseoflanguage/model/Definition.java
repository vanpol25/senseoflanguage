package com.senseoflanguage.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Definition implements Serializable {

    @Field(name = "definition", targetType = FieldType.STRING)
    private String definition;
    @Field(name = "partOfSpeech", targetType = FieldType.STRING)
    private String partOfSpeech;
    @Field(name = "synonyms")
    private Set<String> synonyms = new HashSet<>();
    @Field(name = "antonyms")
    private Set<String> antonyms = new HashSet<>();
    @Field(name = "typeOf")
    private Set<String> typeOf = new HashSet<>();
    @Field(name = "hasTypes")
    private Set<String> hasTypes = new HashSet<>();
    @Field(name = "similarTo")
    private Set<String> similarTo = new HashSet<>();
    @Field(name = "examples")
    private Set<String> examples = new HashSet<>();

}
