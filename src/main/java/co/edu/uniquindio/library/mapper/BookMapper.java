package co.edu.uniquindio.library.mapper;


import co.edu.uniquindio.library.dto.CreateBookDTO;
import co.edu.uniquindio.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(CreateBookDTO dto);
    CreateBookDTO toDTO(Book book);
}
