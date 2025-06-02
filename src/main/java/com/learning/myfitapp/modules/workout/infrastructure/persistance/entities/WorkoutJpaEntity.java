package com.learning.myfitapp.modules.workout.infrastructure.persistance.entities;

import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = WorkoutJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutJpaEntity {

    public static final String TABLE_NAME = "workout";

    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String PROFILE_ID_COL = "profile_id";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = NAME_COL, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROFILE_ID_COL, nullable = false)
    private ProfileJpaEntity profile;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExerciseJpaEntity> exercises = new ArrayList<>();
}
