package app.workout.controller;

import app.workout.dtos.workout.WorkoutCreateDto;
import app.workout.dtos.workout.WorkoutResponseDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseCreateDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.service.WorkoutExerciseService;
import org.apache.coyote.Response;
import org.apache.http.protocol.ResponseServer;
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

    @PatchMapping("/{externalWorkoutExerciseId}")
    public ResponseEntity<WorkoutExerciseResponseDto> updateWorkoutExercise(@PathVariable UUID externalWorkoutExerciseId, WorkoutExerciseResponseDto workoutExerciseResponseDto){
        WorkoutExerciseResponseDto updatedWorkoutExercise = workoutExerciseService.updateWorkoutExercise(externalWorkoutExerciseId, workoutExerciseResponseDto);
        return ResponseEntity.ok(updatedWorkoutExercise);
    }

    @DeleteMapping("/{externalWorkoutExerciseId}")
    public ResponseEntity<String> deleteWorkoutExercise(@PathVariable UUID externalWorkoutExerciseId){
        workoutExerciseService.deleteWorkoutExercise(externalWorkoutExerciseId);
        return ResponseEntity.ok("Deleted");
    }
}
