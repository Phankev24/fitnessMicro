package app.workout.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    private Integer reps;
    private Double weight;

    //many workoutExercises can map to one exercise
    @ManyToOne
    private WorkoutExercise workoutExercise;

    public Exercise(Double weight, Integer reps) {
        this.weight = weight;
        this.reps = reps;
    }
}
