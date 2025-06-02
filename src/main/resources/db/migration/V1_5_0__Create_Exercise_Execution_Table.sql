CREATE TABLE exercise_execution (
    id UUID PRIMARY KEY,
    reps INTEGER,
    weight DOUBLE PRECISION,
    workout_exercise_id UUID NOT NULL,
    workout_session_id UUID NOT NULL,
    CONSTRAINT fk_exec_exercise FOREIGN KEY (workout_exercise_id) REFERENCES workout_exercise(id) ON DELETE CASCADE,
    CONSTRAINT fk_exec_session FOREIGN KEY (workout_session_id) REFERENCES workout_session(id) ON DELETE CASCADE
);