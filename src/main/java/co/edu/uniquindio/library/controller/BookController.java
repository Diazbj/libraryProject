package co.edu.uniquindio.library.controller;


import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.MessageDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;
import co.edu.uniquindio.library.dto.SearchBookSimpleDTO;
import co.edu.uniquindio.library.model.Book;
import co.edu.uniquindio.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    @Operation(summary = "search books by title, author or isbn")
    public ResponseEntity<MessageDTO<List<CreateBookDTO>>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn) throws Exception {
        SearchBookDTO searchBook = new SearchBookDTO(title, author, isbn);
        List<CreateBookDTO> books = bookService.searchBook(searchBook);
        return ResponseEntity.ok(new MessageDTO<>(false, books));
    }

    @GetMapping("/search-simple")
    @Operation(summary = "Buscar libros por título o autor")
    public ResponseEntity<MessageDTO<List<CreateBookDTO>>> searchBooksSimple(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) throws Exception {

        SearchBookSimpleDTO searchBook = new SearchBookSimpleDTO(title, author);
        List<CreateBookDTO> results = bookService.searchBookSimple(searchBook);

        return ResponseEntity.ok(new MessageDTO<>(false, results));
    }

}
