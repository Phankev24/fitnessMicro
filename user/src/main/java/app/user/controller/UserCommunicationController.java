package app.user.controller;

import app.user.client.UserClient;
import app.user.dtos.user.UserResponseDto;
import app.user.dtos.workout.*;
import app.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "GET all users and their workout history")
    @GetMapping("/workout")
    public ResponseEntity<List<UserWorkoutResponseDto>> getAllUsersWithWorkouts(){
        List<UserResponseDto> allUsers = userService.getAllUsers();
        List<WorkoutResponseDto> allWorkouts = userClient.getWorkouts();

        Map<UUID, List<WorkoutResponseDto>> workoutByUserId = allWorkouts
                .stream()
                .collect(Collectors.groupingBy(WorkoutResponseDto::externalUserId
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

    @Operation(description = "GET one user and their Workout history")
    @GetMapping("/{externalUserId}/userWorkout")
    public ResponseEntity<UserWorkoutResponseDto> getUserWithWorkouts(@PathVariable UUID userId){
        UserResponseDto userDto = userService.getUserByExternalId(userId);
        List<WorkoutResponseDto> workouts = userClient.getWorkoutsByUserId(userId);

        return ResponseEntity.ok(new UserWorkoutResponseDto(userDto, workouts));
    }

    @Operation(description = "POST a workout for a User. Links with the users UUID")
    @PostMapping("/{externalUserId}/workout")
    public ResponseEntity<WorkoutResponseDto> createWorkoutForUser(@PathVariable UUID externalUserId,@RequestBody WorkoutCreateDto workoutCreateDto) {
        WorkoutResponseDto createdWorkout = userClient.userCreateWorkout(externalUserId, workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }


    @Operation(description = "POST workoutExercise (log) for user")
    @PostMapping("/{externalWorkoutId}/workoutExercise")
    public ResponseEntity<WorkoutExerciseResponseDto> createWorkoutExerciseForUser(@PathVariable UUID externalWorkoutId, @RequestBody WorkoutExerciseCreateDto workoutExerciseCreateDto){
        WorkoutExerciseResponseDto createdWorkoutExercise = userClient.userCreateWorkoutExercise(externalWorkoutId, workoutExerciseCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutExercise);
    }


    @Operation(description = "POST a exercise (set) for User, links with workoutExerciseId Currently. later on pathVariable")
    @PostMapping("/{externalWorkoutExerciseId}/exercise")
    public ResponseEntity<ExerciseResponseDto> createExercise(@PathVariable UUID externalWorkoutExerciseId, @RequestBody ExerciseCreateDto exerciseCreateDto){
        ExerciseResponseDto createdExercise = userClient.createExercise(externalWorkoutExerciseId, exerciseCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @Operation(description = "DELETE a workout by its UUID")
    @DeleteMapping("/{externalWorkoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID externalWorkoutId){
        userClient.deleteWorkout(externalWorkoutId);
        return ResponseEntity.noContent().build();
    }
}
