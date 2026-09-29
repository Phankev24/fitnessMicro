package app.user.controller;

import app.user.client.UserClient;
import app.user.dtos.WorkoutResponseDto;
import app.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client/user")
public class UserCommunicationController {

    private final UserService userService;
    private final UserClient userClient;

    public UserCommunicationController(UserService userService, UserClient userClient){
        this.userService = userService;
        this.userClient = userClient;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getAllWorkouts(){
        return ResponseEntity.ok(userClient.getWorkouts());
    }
}
