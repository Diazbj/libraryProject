package co.edu.uniquindio.library.service.impl;

import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.mapper.BookMapper;
import co.edu.uniquindio.library.model.Book;
import co.edu.uniquindio.library.repository.BookRepository;
import co.edu.uniquindio.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
