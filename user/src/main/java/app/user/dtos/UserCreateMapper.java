package app.user.dtos;

import app.user.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper {

    public User toEntity(UserCreateDto userCreateDto){
        if (userCreateDto == null) return null;

        User user = new User();
        user.setUserName(userCreateDto.userName());
        user.setFirstName(userCreateDto.firstName());
        user.setLastName(userCreateDto.lastName());
        user.setEmail(userCreateDto.email());
        user.setPassword(userCreateDto.password());
        user.setPhoneNumber(userCreateDto.phoneNumber());

        return user;
    }
}
