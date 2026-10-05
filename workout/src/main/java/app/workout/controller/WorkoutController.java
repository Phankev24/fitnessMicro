package app.workout.controller;

import app.workout.dtos.WorkoutCreateDto;
import app.workout.dtos.WorkoutResponseDto;
import app.workout.dtos.WorkoutUpdateDto;
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

    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponseDto> getWorkoutById(@PathVariable Long workoutId){
        return ResponseEntity.ok(workoutService.getWorkoutById(workoutId));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDto> createWorkout(@RequestBody WorkoutCreateDto workoutCreateDto){
        WorkoutResponseDto createdWorkout = workoutService.createWorkout(workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }

    @PatchMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponseDto> updateWorkout(@PathVariable Long workoutId, @RequestBody WorkoutUpdateDto workoutUpdateDto){
        WorkoutResponseDto updatedWorkout = workoutService.updateWorkout(workoutId, workoutUpdateDto);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long workoutId){
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutResponseDto>> getWorkoutsByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(workoutService.getWorkoutByUserId(userId));
    }
}
