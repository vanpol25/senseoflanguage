package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "words")
public class Word implements Serializable {

    @Id
    private String id;
    @Indexed
    @Field(name = "eng", targetType = FieldType.STRING)
    private String eng;
    @Field(name = "ukr", targetType = FieldType.STRING)
    private String ukr;
    @Field(name = "translit", targetType = FieldType.STRING)
    private String translit;
    @Indexed
    @Field(name = "frequency", targetType = FieldType.DECIMAL128)
    private BigDecimal frequency;
    @Indexed
    @Field(name = "collections")
    private Set<CollectionType> collections = new HashSet<>();
    @Field(name = "results")
    private Set<Definition> results = new HashSet<>();

}
