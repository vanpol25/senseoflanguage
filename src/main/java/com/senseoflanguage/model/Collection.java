package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Collection implements Serializable {

    private CollectionType name;
    private Set<WordInfo> words = new HashSet<>();

}
