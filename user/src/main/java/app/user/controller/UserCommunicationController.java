package app.user.controller;

import app.user.client.UserClient;
import app.user.dtos.user.UserResponseDto;
import app.user.dtos.user.UserWorkoutRequestDto;
import app.user.dtos.workout.UserWorkoutResponseDto;
import app.user.dtos.workout.WorkoutCreateDto;
import app.user.dtos.workout.WorkoutResponseDto;
import app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client/user")
public class UserCommunicationController {

    private final UserService userService;
    private final UserClient userClient;

    public UserCommunicationController(UserService userService, UserClient userClient){
        this.userService = userService;
        this.userClient = userClient;
    }

    @GetMapping("/workout")
    public ResponseEntity<List<UserWorkoutResponseDto>> getAllUsersWithWorkouts(){
        List<UserResponseDto> allUsers = userService.getAllUsers();
        List<WorkoutResponseDto> allWorkouts = userClient.getWorkouts();

        Map<UUID, List<WorkoutResponseDto>> workoutByUserId = allWorkouts
                .stream()
                .collect(Collectors.groupingBy(WorkoutResponseDto::userId
                ));

        List<UserWorkoutResponseDto> result = allUsers
                .stream()
                .map(user -> new UserWorkoutResponseDto(
                        user,
                        workoutByUserId.getOrDefault(user.externalUserId(), Collections.emptyList())

                ))
                .toList();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{userId}/userWorkout")
    public ResponseEntity<UserWorkoutResponseDto> getUserWithWorkouts(@PathVariable UUID userId){
        UserResponseDto userDto = userService.getUserByExternalId(userId);
        List<WorkoutResponseDto> workouts = userClient.getWorkoutsByUserId(userId);

        return ResponseEntity.ok(new UserWorkoutResponseDto(userDto, workouts));
    }


    @PostMapping("/{userId}/workout")
    public ResponseEntity<WorkoutResponseDto> createWorkoutForUser(@PathVariable UUID userId,@RequestBody UserWorkoutRequestDto userWorkoutRequestDto) {
        WorkoutCreateDto workoutCreateDto = new WorkoutCreateDto(
                userId,
                userWorkoutRequestDto.workoutName(),
                userWorkoutRequestDto.workoutDescription(),
                userWorkoutRequestDto.workoutType()
        );

        WorkoutResponseDto createdWorkout = userClient.userCreateWorkout(workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }
}
