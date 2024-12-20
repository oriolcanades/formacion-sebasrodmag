package es.formacion.demo.model.dto;

public record AppUserRequest(
        String username,
        int password
) {
}
