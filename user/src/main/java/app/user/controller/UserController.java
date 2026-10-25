package app.user.controller;

import app.user.dtos.user.UserCreateDto;
import app.user.dtos.user.UserResponseDto;
import app.user.dtos.user.UserUpdateDto;
import app.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(description = "See all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(description = "See a user by its Long userId")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(description = "Create a user")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto){
        UserResponseDto createdUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(description = "Update the user")
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto){
        UserResponseDto updatedUser = userService.updateUser(id, userUpdateDto);

        return ResponseEntity.ok(updatedUser);
    }

    @Operation(description = "Deletes the user")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}