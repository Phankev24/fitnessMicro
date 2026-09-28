package app.user.dtos;

public record UserUpdateDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {}
