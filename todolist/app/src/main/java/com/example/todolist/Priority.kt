package com.example.todolist

enum class Priority(ordering: Int){
    HIGH(2),
    MIDDLE(1),
    LOW(0);

    val ordering = ordering
}