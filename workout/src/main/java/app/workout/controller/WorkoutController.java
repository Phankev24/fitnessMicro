package app.workout.controller;

import app.workout.dtos.workout.WorkoutCreateDto;
import app.workout.dtos.workout.WorkoutResponseDto;
import app.workout.dtos.workout.WorkoutUpdateDto;
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

    @GetMapping("/{externalWorkoutId}")
    public ResponseEntity<WorkoutResponseDto> getWorkoutById(@PathVariable UUID externalWorkoutId){
        return ResponseEntity.ok(workoutService.getWorkoutById(externalWorkoutId));
    }

    @PostMapping("/user/{externalUserId}")
    public ResponseEntity<WorkoutResponseDto> createWorkout(@PathVariable UUID externalUserId, @RequestBody WorkoutCreateDto workoutCreateDto){
        WorkoutResponseDto createdWorkout = workoutService.createWorkout(externalUserId, workoutCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }

    @PatchMapping("/{externalWorkoutId}")
    public ResponseEntity<WorkoutResponseDto> updateWorkout(@PathVariable UUID externalWorkoutId, @RequestBody WorkoutUpdateDto workoutUpdateDto){
        WorkoutResponseDto updatedWorkout = workoutService.updateWorkout(externalWorkoutId, workoutUpdateDto);
        return ResponseEntity.ok(updatedWorkout);
    }

    @DeleteMapping("/{externalWorkoutId}")
    public ResponseEntity<String> deleteWorkout(@PathVariable UUID externalWorkoutId){
        workoutService.deleteWorkout(externalWorkoutId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{externalUserId}")
    public ResponseEntity<List<WorkoutResponseDto>> getWorkoutsByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(workoutService.getWorkoutByUserId(userId));
    }
}
