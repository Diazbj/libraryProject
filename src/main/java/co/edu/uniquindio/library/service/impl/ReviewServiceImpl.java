package co.edu.uniquindio.library.service.impl;

import co.edu.uniquindio.library.dto.AddReviewDTO;
import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.ReviewDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;
import co.edu.uniquindio.library.mapper.BookMapper;
import co.edu.uniquindio.library.model.Book;
import co.edu.uniquindio.library.model.Review;
import co.edu.uniquindio.library.repository.BookRepository;
import co.edu.uniquindio.library.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl  implements ReviewService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public CreateBookDTO previewReview(AddReviewDTO dto) throws Exception {
        Book book = findOrCreateInMemory(dto); // no guarda en BD
        appendReview(book, dto.review());
        return bookMapper.toDTO(book);
    }

    @Override
    public CreateBookDTO addReview(AddReviewDTO dto) throws Exception {
        Book book = findOrCreatePersistent(dto); // busca o crea en BD
        appendReview(book, dto.review());
        Book saved = bookRepository.save(book);
        return bookMapper.toDTO(saved);
    }


    //TODO----------------------MÉTODOS AUXILIARES--------------------------------
    private Book findOrCreateInMemory(AddReviewDTO dto) throws Exception {
        validateAddReviewDTO(dto);
        Optional<Book> found = findExisting(dto);
        return found.map(this::copy).orElseGet(() -> createNewFrom(dto));
    }

    private Book findOrCreatePersistent(AddReviewDTO dto) throws Exception {
        validateAddReviewDTO(dto);
        Optional<Book> found = findExisting(dto);
        return found.orElseGet(() -> bookRepository.save(createNewFrom(dto)));
    }

    private Optional<Book> findExisting(AddReviewDTO dto) {
        if (dto.isbn() != null && !dto.isbn().isBlank()) {
            Optional<Book> byIsbnExact = bookRepository.findByIsbn(dto.isbn());
            if (byIsbnExact.isPresent()) return byIsbnExact;
        }
        if (dto.title() != null && !dto.title().isBlank()) {
            List<Book> candidates = bookRepository.findByTitleContainingIgnoreCase(dto.title());
            if (!candidates.isEmpty()) {
                if (dto.author() != null && !dto.author().isBlank()) {
                    return candidates.stream()
                            .filter(b -> b.getAuthor() != null &&
                                    b.getAuthor().equalsIgnoreCase(dto.author()))
                            .findFirst()
                            .or(() -> Optional.of(candidates.get(0)));
                }
                return Optional.of(candidates.get(0));
            }
        }
        return Optional.empty();
    }

    private Book createNewFrom(AddReviewDTO dto) {
        Book b = new Book();
        b.setTitle(dto.title());
        b.setAuthor(dto.author());
        b.setIsbn(dto.isbn());
        b.setReviews(new ArrayList<>());
        return b;
    }

    private Book copy(Book original) {
        Book b = new Book();
        b.setId(original.getId());
        b.setTitle(original.getTitle());
        b.setAuthor(original.getAuthor());
        b.setIsbn(original.getIsbn());
        b.setReviews(original.getReviews() != null ? new ArrayList<>(original.getReviews())
                : new ArrayList<>());
        return b;
    }

    private void appendReview(Book book, ReviewDTO reviewDTO) {
        if (book.getReviews() == null) book.setReviews(new ArrayList<>());
        Review r = Review.builder()
                .rating(reviewDTO.rating())
                .comment(reviewDTO.comment())
                .build();
        book.getReviews().add(r);
    }

    private void validateAddReviewDTO(AddReviewDTO dto) throws IllegalArgumentException {
        boolean hasIsbn = dto.isbn() != null && !dto.isbn().isBlank();
        boolean hasTitle = dto.title() != null && !dto.title().isBlank();
        if (!hasIsbn && !hasTitle) {
            throw new IllegalArgumentException("Debes enviar al menos 'isbn' o 'title' para identificar/crear el libro.");
        }
    }

}
