package com.eks.mvvm_course.viewmodel.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eks.mvvm_course.model.Todo
import com.eks.mvvm_course.model.TodoRepository
import com.eks.mvvm_course.util.Routes
import com.eks.mvvm_course.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    ViewModel contains the business logic
    ViewModel keeps the state to avoid Configuration Change side-effects
 */

@HiltViewModel // tells hilt this is the ViewMode
class TodoListViewModel @Inject constructor(
// this is how we inject dependencies, hilt knows how to create this because we "taught" it how to do so in AppModule
    private val repository: TodoRepository,
) : ViewModel() {

//    val todos = repository.readTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var deletedTodo: Todo? = null // acts like cache

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                // _uiEvent.send(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
                // cant use this cuz we need a coroutine for the suspend function
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnDoneChangeClick -> {
                viewModelScope.launch {
                    repository.createTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    ) //in the dao we used the onconflict parameter so we can use create to update
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            msg = "Todo deleted",
                            action = "undo"
                        )
                    )
                }
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.createTodo(todo)
                    }
                }
            }
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}