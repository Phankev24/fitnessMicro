package app.user.dtos;

public record UserCreateDto(
        String userName,
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber
) {
}
