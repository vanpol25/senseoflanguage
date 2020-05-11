package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "profiles")
public class Profile implements Serializable {

    @Id
    private String id;
    @Field(name = "firstName", targetType = FieldType.STRING)
    private String firstName;
    @Field(name = "lastName", targetType = FieldType.STRING)
    private String lastName;
    @Field(name = "userName", targetType = FieldType.STRING)
    private String userName;
    @DBRef
    private List<Collection> collections = new ArrayList<>();
    @Indexed
    @Field(name = "currentCollection", targetType = FieldType.STRING)
    private CollectionType currentCollection;
    @Indexed
    @Field(name = "currentWordId", targetType = FieldType.STRING)
    private String currentWordId;

}
