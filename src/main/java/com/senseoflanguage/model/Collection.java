package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.WordState;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Collection implements Serializable {

    private String name;
    private List<Long> wordIds = new ArrayList<>();
    private WordState wordState;
    private Set<WordInfo> words = new HashSet<>();

}
