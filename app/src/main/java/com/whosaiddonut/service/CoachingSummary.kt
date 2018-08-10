package com.whosaiddonut.service

data class CoachingSummary(
        val activeTodo: Boolean = false,
        val activeChat: Boolean = false,
        val numberOfTodoItems: Int = 0,
        val numberOfCompletedTodoItems: Int = 0,
        val selected: Boolean = false
)