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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "photo")
public class Photo implements IEntity {

    @Transient
    public static final String SEQUENCE_NAME = "photo_sequence";

    @Id
    private int id;

    private String title;

    @Field("photo_path")
    private String photoPath;
}
