package co.edu.uniquindio.library.service.impl;

import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;
import co.edu.uniquindio.library.dto.SearchBookSimpleDTO;
import co.edu.uniquindio.library.mapper.BookMapper;
import co.edu.uniquindio.library.model.Book;
import co.edu.uniquindio.library.repository.BookRepository;
import co.edu.uniquindio.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Override
    public void createBook(CreateBookDTO createBook) throws Exception {
        if (createBook.title() == null || createBook.author() == null) {
            throw new Exception("El título y el autor son obligatorios");
        }

        Book book = bookMapper.toEntity(createBook);

        bookRepository.save(book);
    }

    @Override
    public List<CreateBookDTO> searchBook(SearchBookDTO searchBook) throws Exception {
        List<Book> books;
        if (searchBook.isbn() == null
                && searchBook.title() == null
                && searchBook.author() == null) {
            throw new Exception("Debe ingresar al menos un criterio de búsqueda");
        }

        if (searchBook.isbn() != null) {
            books = bookRepository.findByIsbnContainingIgnoreCase(searchBook.isbn());
        } else if (searchBook.title() != null) {
            books = bookRepository.findByTitleContainingIgnoreCase(searchBook.title());
        } else if (searchBook.author() != null) {
            books = bookRepository.findByAuthorContainingIgnoreCase(searchBook.author());
        } else {
            books = bookRepository.findAll();
        }

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<CreateBookDTO> searchBookSimple(SearchBookSimpleDTO searchBook) throws Exception {
        List<Book> books;

        if ((searchBook.title() == null || searchBook.title().isBlank()) &&
                (searchBook.author() == null || searchBook.author().isBlank())) {
            throw new Exception("Debe ingresar al menos título o autor para la búsqueda");
        }

        if (searchBook.title() != null && !searchBook.title().isBlank() &&
                searchBook.author() != null && !searchBook.author().isBlank()) {
            // Busca por título o autor
            books = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                    searchBook.title(), searchBook.author()
            );
        } else if (searchBook.title() != null && !searchBook.title().isBlank()) {
            books = bookRepository.findByTitleContainingIgnoreCase(searchBook.title());
        } else {
            books = bookRepository.findByAuthorContainingIgnoreCase(searchBook.author());
        }

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
