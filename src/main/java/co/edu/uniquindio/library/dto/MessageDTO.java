package co.edu.uniquindio.library.dto;

public record MessageDTO<T>(
        boolean error,
        T mensaje
) {
}
