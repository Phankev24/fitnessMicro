package app.user.dtos.user;

import java.util.UUID;


public record UserResponseDto(
        Long internalUserId,
        UUID externalUserId,
        String userName,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password
){}
