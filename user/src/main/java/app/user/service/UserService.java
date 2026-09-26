package app.user.service;

import app.user.dtos.UserResponseDto;
import app.user.dtos.UserResponseMapper;
import app.user.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    public UserService(UserRepository userRepository, UserResponseMapper userResponseMapper){
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
    }

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userResponseMapper::toDTO)
                .toList();
    }
}
