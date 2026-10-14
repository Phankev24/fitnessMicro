package app.user.dtos.user;

public record UserUpdateDto(
        String userName,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {}
