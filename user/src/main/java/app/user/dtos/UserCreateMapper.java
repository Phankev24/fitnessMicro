package app.user.dtos;

import app.user.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper {

    public UserCreateDto toDTO(User user){
        if(user == null) return null;

        return new UserCreateDto(
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber()
        );
    }

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
