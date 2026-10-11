package app.user.dtos;

public record UserUpdateDto(
        String userName,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {}
