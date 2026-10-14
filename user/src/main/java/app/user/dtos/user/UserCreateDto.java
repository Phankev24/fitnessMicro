package app.user.dtos.user;

public record UserCreateDto(
        String userName,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password
) {
}
