package app.user.dtos;

import app.user.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserResponseDto toDTO(User user){
        if(user == null) return null;

        return new UserResponseDto(
                user.getInternalUserId(),
                user.getExternalUserId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber()
        );
    }

}
