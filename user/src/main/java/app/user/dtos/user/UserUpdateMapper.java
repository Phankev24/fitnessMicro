package app.user.dtos.user;

import app.user.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateMapper {
    public void updateEntityFromDto(UserUpdateDto userUpdateDto, User user){
        if(userUpdateDto.userName() != null){
            user.setUserName(userUpdateDto.userName());
        }
        if(userUpdateDto.firstName() != null){
            user.setFirstName(userUpdateDto.firstName());
        }

        if(userUpdateDto.lastName() != null){
            user.setLastName(userUpdateDto.lastName());
        }

        if(userUpdateDto.phoneNumber() != null){
            user.setPhoneNumber(userUpdateDto.phoneNumber());
        }

        if(userUpdateDto.email() != null){
            user.setEmail(userUpdateDto.email());
        }
    }
}
