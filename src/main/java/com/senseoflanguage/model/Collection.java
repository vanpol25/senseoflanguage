package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "collections")
public class Collection implements Serializable {

    @Id
    private String id;
    @Indexed
    @Field(name = "name", targetType = FieldType.STRING)
    private CollectionType collectionType;
    @Field(name = "words")
    private Set<WordInfo> words = new HashSet<>();

}
