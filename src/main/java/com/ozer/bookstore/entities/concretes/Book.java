package com.ozer.bookstore.entities.concretes;

import com.ozer.bookstore.entities.abstracts.IEntity;
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
//@Document(collection = "book")
public class Book implements IEntity {

    @Transient // veri deposu motoru bu alanı okumaz
    public static final String SEQUENCE_NAME = "book_sequence";

    @Id
    private int id;

    private String title;

    @Field("author") // field mongoDb tarafında bu alan adını yapılandırmak için kullanılır
    private List<String> authors;

    @Field("published_date")
    private String publishDate;

    @Field("pages")
    private int pagesNo;

    private String language;

    @Field("publisher_id")
    private String publisherId;

    @Field("available")
    int stock;
}
