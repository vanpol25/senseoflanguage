package com.senseoflanguage.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.telegram.telegrambots.meta.api.objects.User;

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

}
