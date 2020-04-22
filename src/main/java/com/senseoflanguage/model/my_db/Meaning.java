package com.senseoflanguage.model.my_db;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Meaning implements Serializable {

    private String definition;
    private String example;
    private List<String> synonyms = new ArrayList<>();

}
