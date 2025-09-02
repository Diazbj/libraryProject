package co.edu.uniquindio.library.repository;


import co.edu.uniquindio.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByIsbnContainingIgnoreCase(String isbn);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
