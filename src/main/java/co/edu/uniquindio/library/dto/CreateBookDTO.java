package co.edu.uniquindio.library.dto;


import java.util.List;

public record CreateBookDTO(
        String id,
        String title,
        String author,
        String isbn,
        List<ReviewDTO> reviews
) {
}

