package co.edu.uniquindio.library.exceptions;


import co.edu.uniquindio.library.dto.MessageDTO;
import co.edu.uniquindio.library.dto.ValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MessageDTO<String>> noResourceFoundExceptionHandler (NoResourceFoundException ex){
        return ResponseEntity.status(404).body( new MessageDTO<>(true, "El recurso no fue encontrado") );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO<String>> generalExceptionHandler (Exception e){
        return ResponseEntity.internalServerError().body( new MessageDTO<>(true, e.getMessage()) );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO<List<ValidationDTO>>> validationExceptionHandler (MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();


        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidationDTO(e.getField(), e.getDefaultMessage()) );
        }


        return ResponseEntity.badRequest().body( new MessageDTO<>(true, errores) );
    }


}
