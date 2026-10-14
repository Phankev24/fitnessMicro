package app.user.dtos.user;

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
        user.setPhoneNumber(userCreateDto.phoneNumber());
        user.setEmail(userCreateDto.email());
        user.setPassword(userCreateDto.password());

        return user;
    }
}
