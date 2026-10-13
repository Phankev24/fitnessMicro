package app.user.service;

import app.user.dtos.user.*;
import app.user.user.User;
import app.user.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserCreateMapper userCreateMapper;
    private final UserUpdateMapper userUpdateMapper;

    public UserService(UserRepository userRepository, UserResponseMapper userResponseMapper, UserCreateMapper userCreateMapper, UserUpdateMapper userUpdateMapper){
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userCreateMapper = userCreateMapper;
        this.userUpdateMapper = userUpdateMapper;
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


    public UserResponseDto getUserByExternalId(UUID externalUserId){
        return userRepository.findByExternalUserId(externalUserId)
                .map(userResponseMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with external id: " + externalUserId
                ));
    }


    public UserResponseDto createUser(UserCreateDto userCreateDto){
        User userEntity = userCreateMapper.toEntity(userCreateDto);
        User savedUser = userRepository.save(userEntity);
        return userResponseMapper.toDTO(savedUser);
    }


    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with this id" + id));
        userUpdateMapper.updateEntityFromDto(userUpdateDto, user);
        User upatedUser = userRepository.save(user);
        return userResponseMapper.toDTO(upatedUser);
    }


    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
        userRepository.delete(user);
    }
}