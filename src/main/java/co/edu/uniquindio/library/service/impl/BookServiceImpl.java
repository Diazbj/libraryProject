package co.edu.uniquindio.library.service.impl;

import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;
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
        List<Book> books = bookRepository.searchBooks(
                searchBook.title(),
                searchBook.author(),
                searchBook.isbn()
        );

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
