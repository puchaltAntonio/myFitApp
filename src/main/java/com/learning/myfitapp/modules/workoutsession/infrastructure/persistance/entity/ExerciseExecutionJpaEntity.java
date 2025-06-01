package com.learning.myfitapp.modules.workoutsession.infrastructure.persistance.entity;

import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = ExerciseExecutionJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseExecutionJpaEntity {

    public static final String TABLE_NAME = "exercise_execution";

    public static final String ID_COL = "id";
    public static final String REPS_COL= "reps";
    public static final String WEIGHT_COL= "weight";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = REPS_COL)
    private int repetitions;

    @Column(name = WEIGHT_COL)
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExerciseJpaEntity workoutExerciseJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSessionJpaEntity workoutSession;
}
