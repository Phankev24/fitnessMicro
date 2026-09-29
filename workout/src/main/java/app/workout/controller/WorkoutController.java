package app.workout.controller;

import app.workout.dtos.WorkoutCreateDto;
import app.workout.dtos.WorkoutResponseDto;
import app.workout.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getAllWorkout(){
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutResponseDto>> getWorkoutsByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(workoutService.getWorkoutByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDto> createWorkout(@RequestBody WorkoutCreateDto workoutCreateDto){
        WorkoutResponseDto createdWorkout = workoutService.createWorkout(workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }
}
