package app.user.controller;

import app.user.client.UserClient;
import app.user.client.WorkoutClient;
import app.user.dtos.user.UserResponseDto;
import app.user.dtos.user.UserWorkoutRequestDto;
import app.user.dtos.workout.UserWorkoutResponseDto;
import app.user.dtos.workout.WorkoutCreateDto;
import app.user.dtos.workout.WorkoutResponseDto;
import app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client/user")
public class UserCommunicationController {

    private final UserService userService;
    private final UserClient userClient;
    private final WorkoutClient workoutClient;

    public UserCommunicationController(UserService userService, UserClient userClient, WorkoutClient workoutClient){
        this.userService = userService;
        this.userClient = userClient;
        this.workoutClient = workoutClient;
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserWorkoutResponseDto> getUserWithWorkouts(@PathVariable UUID userId){
        UserResponseDto userDto = userService.getUserByExternalId(userId);
        List<WorkoutResponseDto> workouts = workoutClient.getWorkoutsByUserId(userId);

        return ResponseEntity.ok(new UserWorkoutResponseDto(userDto, workouts));
    }

    @GetMapping("/workout")
    public ResponseEntity<List<WorkoutResponseDto>> getAllWorkouts(){
        return ResponseEntity.ok(userClient.getWorkouts());
    }

    @GetMapping("/{userId}/workout")
    public ResponseEntity<List<WorkoutResponseDto>> getWorkoutsByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(userClient.getWorkoutsByUserId(userId));
    }

    @PostMapping("/{userId}/workout")
    public ResponseEntity<WorkoutResponseDto> createWorkoutForUser(@PathVariable UUID userId,@RequestBody UserWorkoutRequestDto userWorkoutRequestDto) {
        WorkoutCreateDto workoutCreateDto = new WorkoutCreateDto(
                userId,
                userWorkoutRequestDto.workoutName(),
                userWorkoutRequestDto.workoutDescription(),
                userWorkoutRequestDto.workoutType()
        );

        WorkoutResponseDto createdWorkout = userClient.createWorkout(workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }
}
