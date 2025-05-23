CREATE TABLE exercise (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    primary_muscle VARCHAR(50) NOT NULL,
    secondary_muscle VARCHAR(50) NOT NULL,
    equipment VARCHAR(50) NOT NULL,
    exercise_animation VARCHAR(255)
);