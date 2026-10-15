package app.workout.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutExerciseId;
    
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
}
