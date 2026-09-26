package app.user.dtos;

import java.util.UUID;


public record UserResponseDto(
        Long internalUserId,
        UUID externalUserId,
        String userName,
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber
){}
