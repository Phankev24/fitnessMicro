package app.user.service;

import app.user.dtos.UserCreateDto;
import app.user.dtos.UserCreateMapper;
import app.user.dtos.UserResponseDto;
import app.user.dtos.UserResponseMapper;
import app.user.user.User;
import app.user.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserCreateMapper userCreateMapper;

    public UserService(UserRepository userRepository, UserResponseMapper userResponseMapper, UserCreateMapper userCreateMapper){
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userCreateMapper = userCreateMapper;
    }

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userResponseMapper::toDTO)
                .toList();
    }

    public UserResponseDto getUserById(Long id){
        return userRepository.findById(id)
                .map(userResponseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserResponseDto createUser(UserCreateDto userCreateDto){
        User userEntity = userCreateMapper.toEntity(userCreateDto);
        User savedUser = userRepository.save(userEntity);
        return userResponseMapper.toDTO(savedUser);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
        userRepository.delete(user);
    }
}