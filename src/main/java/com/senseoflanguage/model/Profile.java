package com.senseoflanguage.model;

import com.senseoflanguage.model.enums.CollectionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "profiles")
public class Profile implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private Set<Collection> collections = new HashSet<>();
    private CollectionType currentCollection;
    private String currentWordId;

}
