CREATE TABLE workout_exercise (
    id UUID PRIMARY KEY,
    workout_id UUID NOT NULL,
    exercise_id UUID NOT NULL,
    "order" INTEGER NOT NULL,
    CONSTRAINT fk_workout_exercise_workout FOREIGN KEY (workout_id) REFERENCES workout(id) ON DELETE CASCADE,
    CONSTRAINT fk_workout_exercise_exercise FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE
);