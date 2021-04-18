package com.example.todolist

enum class TaskType(image: Int) {
    FOOD(R.drawable.food),
    HOME(R.drawable.home),
    LEARNING(R.drawable.learning),
    MEDICAL(R.drawable.medical),
    MEETING(R.drawable.meeting),
    OTHER(R.drawable.other),
    PET(R.drawable.pet),
    SHOPPING(R.drawable.shopping),
    SPORT(R.drawable.sport),
    TRAVEL(R.drawable.travel);

    val image = image
}