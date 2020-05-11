package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.WordState;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WordInfo implements Serializable {

    @Field(name = "wordId", targetType = FieldType.OBJECT_ID)
    private String wordId;
    @Field(name = "timesToSolve", targetType = FieldType.INT32)
    private Integer timesToSolve;
    @Field(name = "wordState", targetType = FieldType.STRING)
    private WordState wordState;

}
