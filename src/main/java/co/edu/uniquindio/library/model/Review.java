package co.edu.uniquindio.library.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Review {
    private int rating;
    private String comment;
}
