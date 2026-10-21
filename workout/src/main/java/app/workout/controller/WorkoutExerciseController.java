package app.workout.controller;

import app.workout.dtos.workout.WorkoutCreateDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseCreateDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.service.WorkoutExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workoutExercise")
public class WorkoutExerciseController {
    private final WorkoutExerciseService workoutExerciseService;
    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutExerciseResponseDto>> getAllWorkoutExercises(){
        return ResponseEntity.ok(workoutExerciseService.getAllWorkoutSessions());
    }

    @PostMapping("/user/{externalWorkoutId}")
    public ResponseEntity<WorkoutExerciseResponseDto> createWorkoutSession(@PathVariable UUID externalWorkoutId , @RequestBody WorkoutExerciseCreateDto workoutExerciseCreateDto){
        WorkoutExerciseResponseDto createdWorkoutSession = workoutExerciseService.createWorkoutSession(externalWorkoutId, workoutExerciseCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutSession);
    }
}
