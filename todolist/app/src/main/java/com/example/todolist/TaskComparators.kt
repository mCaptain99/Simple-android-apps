package com.example.todolist

class TaskComparators {
    companion object {
        val dateComparator = Comparator { task1: Task, task2: Task ->
            (task1.date.timeInMillis - task2.date.timeInMillis).toInt()
        }

        val priorityComparator = Comparator { task1: Task, task2: Task ->
            task1.priority.ordering - task2.priority.ordering
        }

        val typeComparator = Comparator { task1: Task, task2: Task ->
            task1.type.image - task2.type.image
        }
    }

}