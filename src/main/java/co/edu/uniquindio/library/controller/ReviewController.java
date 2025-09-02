package co.edu.uniquindio.library.controller;

import co.edu.uniquindio.library.dto.AddReviewDTO;
import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.MessageDTO;
import co.edu.uniquindio.library.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/preview")
    @Operation(summary = "Previsualizar reseña (NO guarda en la BD)")
    public ResponseEntity<MessageDTO<CreateBookDTO>> preview(@Valid @RequestBody AddReviewDTO dto) throws Exception {
        CreateBookDTO preview = reviewService.previewReview(dto);
        return ResponseEntity.ok(new MessageDTO<>(false, preview));
    }

    @PostMapping
    @Operation(summary = "Agregar reseña (crea el libro si no existe)")
    public ResponseEntity<MessageDTO<CreateBookDTO>> add(@Valid @RequestBody AddReviewDTO dto) throws Exception {
        CreateBookDTO updated = reviewService.addReview(dto);
        return ResponseEntity.ok(new MessageDTO<>(false, updated));
    }
}
