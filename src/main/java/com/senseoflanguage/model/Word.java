package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "eng", "ukr", "translit"})
@ToString
@Document(collection = "words")
public class Word implements Serializable {

    @Id
    private String id;
    private String eng;
    private String ukr;
    private String translit;
    private BigDecimal frequency;
    private Set<CollectionType> collections = new HashSet<>();
    private Set<Definition> results = new HashSet<>();

}
