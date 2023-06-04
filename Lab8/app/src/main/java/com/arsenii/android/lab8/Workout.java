package com.arsenii.android.lab8;

import java.util.List;

public class Workout {
    private static final List<Workout> workout = List.of(
            new Workout("Пресс качат", "\n\n\n120 раз\nПередых минуту\n15 раз"),
            new Workout("Бегит", "\n\n\n100 метров\nПередых 10 минут\n100метров"),
            new Workout("Турник", "\n\n\n1 раз\nПередых 5 минут\n пол раза"),
            new Workout("Анжуманя", "\n\n\n50 раз\nПередых 7 минут\n 10 раза")
    );
    private String name;
    private String description;

    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<Workout> getWorkout() {
        return workout;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
