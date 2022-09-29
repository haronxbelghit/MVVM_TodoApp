package com.eks.mvvm_course.viewmodel.todoList

import com.eks.mvvm_course.model.Todo

/*
    Event class contains some events that we will send from the View to the ViewModel when there's some interaction
    We list all the possible events of the screen
 */

sealed class TodoListEvent {
    data class OnDeleteTodoClick(val todo: Todo) : TodoListEvent()
    data class OnDoneChangeClick(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    object OnUndoDeleteClick : TodoListEvent()
    object OnAddTodoClick : TodoListEvent()
}