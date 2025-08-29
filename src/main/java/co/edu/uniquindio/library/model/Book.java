package co.edu.uniquindio.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Collection;
import java.util.List;


@Document(collection ="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;

    private List<Review> reviews ;
}
