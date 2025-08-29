package co.edu.uniquindio.library.controller;


import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.MessageDTO;
import co.edu.uniquindio.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")

public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(summary = "create book")
    public ResponseEntity<MessageDTO<String>> createBook(@Valid @RequestBody CreateBookDTO book) throws Exception {
        bookService.createBook(book);
        return ResponseEntity.ok(new MessageDTO<>(false,"Libro creado con éxito"));
    }
}
