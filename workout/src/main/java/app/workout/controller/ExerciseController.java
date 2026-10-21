package app.workout.controller;

import app.workout.dtos.exercise.ExerciseCreateDto;
import app.workout.dtos.exercise.ExerciseResponseDto;
import app.workout.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/user/{externalWorkoutExerciseId}")
    public ResponseEntity<ExerciseResponseDto> createExercise(@PathVariable UUID externalWorkoutExerciseId, @RequestBody ExerciseCreateDto exerciseCreateDto){
        ExerciseResponseDto createdExercise = exerciseService.createExercise(externalWorkoutExerciseId ,exerciseCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }
}
