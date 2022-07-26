package com.ozer.bookstore.entities.concretes;

import com.ozer.bookstore.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User implements IEntity {


    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private int id;

    private String name;

    private String email;

    private String username;

    private String password;
}
