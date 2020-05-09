package com.senseoflanguage.model;

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

    private String name;
    private Set<WordInfo> words = new HashSet<>();

}
