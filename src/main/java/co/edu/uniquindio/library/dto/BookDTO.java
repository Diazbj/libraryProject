package co.edu.uniquindio.library.dto;

import lombok.Data;


public record BookDTO (
        String id,
         String title,
         String author,
         String isbn
){

}
