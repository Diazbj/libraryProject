package co.edu.uniquindio.library.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AddReviewDTO(
        String isbn,
        String title,
        String author,
        @NotNull @Valid ReviewDTO review
) {}


