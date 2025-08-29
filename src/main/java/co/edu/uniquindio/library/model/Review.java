package co.edu.uniquindio.library.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Review {
    private int rating;
    private String comment;
}
