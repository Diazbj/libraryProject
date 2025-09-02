package co.edu.uniquindio.library.service;

import co.edu.uniquindio.library.dto.AddReviewDTO;
import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;

import java.util.List;

public interface ReviewService  {
    CreateBookDTO previewReview(AddReviewDTO dto) throws Exception;
    CreateBookDTO addReview(AddReviewDTO dto) throws Exception;
}
