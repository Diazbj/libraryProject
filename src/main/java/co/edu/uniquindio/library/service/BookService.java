package co.edu.uniquindio.library.service;

import co.edu.uniquindio.library.dto.CreateBookDTO;

public interface BookService {

    void createBook(CreateBookDTO createBook) throws Exception;

}
