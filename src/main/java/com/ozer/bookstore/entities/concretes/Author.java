package com.ozer.bookstore.entities.concretes;

import com.ozer.bookstore.core.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "authors")
public class Author implements IEntity {

    @Transient
    public static final String SEQUENCE_NAME = "author_sequence";

    @Id
    private int id;

    private String name;

    @Field("book_count")
    private int bookCount;

    private List<Book> books;

    private String address;


}
