package com.senseoflanguage.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "words")
public class Word implements Serializable {

    @Id
    private String id;
    private String eng;
    private String ukr;
    private String translit;
    private BigDecimal frequency;
    private String collection;
    private Set<Definition> results = new HashSet<>();

}
