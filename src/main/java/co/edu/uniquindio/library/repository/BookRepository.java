package co.edu.uniquindio.library.repository;


import co.edu.uniquindio.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ '$and': [ "
            + " { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { ?0: null } ] },"
            + " { $or: [ { 'author': { $regex: ?1, $options: 'i' } }, { ?1: null } ] },"
            + " { $or: [ { 'isbn': { $regex: ?2, $options: 'i' } }, { ?2: null } ] }"
            + " ] }")
    List<Book> searchBooks(String title, String author, String isbn);

}
