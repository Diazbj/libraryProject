package co.edu.uniquindio.library.service;

import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.dto.SearchBookDTO;
import co.edu.uniquindio.library.dto.SearchBookSimpleDTO;

import java.util.List;

public interface BookService {

    void createBook(CreateBookDTO createBook) throws Exception;
    List<CreateBookDTO> searchBook(SearchBookDTO searchBook) throws Exception;

    List<CreateBookDTO> searchBookSimple(SearchBookSimpleDTO searchBook) throws Exception;


}
