package app.workout.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutExerciseId;

    private UUID externalWorkoutExerciseId;
    private String exerciseName;

    //Bidirectional many workouts map one workout Session
    @ManyToOne
    private Workout workout;

    //One workoutExercise (workout session) can contain many exercises
    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> sets = new ArrayList<>();

    public WorkoutExercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @PrePersist
    public void generateUuid(){
        if(this.externalWorkoutExerciseId == null){
            this.externalWorkoutExerciseId = UUID.randomUUID();
        }
    }
}
