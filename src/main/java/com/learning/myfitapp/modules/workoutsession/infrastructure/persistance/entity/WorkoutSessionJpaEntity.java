package com.learning.myfitapp.modules.workoutsession.infrastructure.persistance.entity;

import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = WorkoutSessionJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSessionJpaEntity {

    public static final String TABLE_NAME = "workout_session";

    public static final String ID_COL = "id";
    public static final String STARTED_AT_COL = "started_at";
    public static final String ENDED_AT_COL = "ended_at";
    public static final String PROFILE_ID_COL = "profile_id";


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = STARTED_AT_COL )
    private LocalDateTime startedAt;

    @Column(name = ENDED_AT_COL)
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROFILE_ID_COL, nullable = false)
    private WorkoutJpaEntity workout;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseExecutionJpaEntity> executions = new ArrayList<>();


}
