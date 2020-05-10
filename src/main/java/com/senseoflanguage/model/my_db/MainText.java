package com.senseoflanguage.model.my_db;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MainText implements Serializable {

    private String eng;
    private String ukr;
    private String translit;
    private Map<String, List<Meaning>> meaning = new HashMap<>();

}
