package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.WordState;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordInfo implements Serializable {

    private String wordId;
    private Integer timesToSolve;
    private WordState wordState;

}
