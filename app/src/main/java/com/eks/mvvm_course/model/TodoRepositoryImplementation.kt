package com.eks.mvvm_course.model

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImplementation(
    private val dao: TodoDAO // to actually access the db || this is Constructor dependency injection
) : TodoRepository {

    override suspend fun createTodo(todo: Todo) {
        dao.createTodo(todo)
    }

    override suspend fun readTodo_byId(id: Int): Todo? {
        return dao.readTodo_byId(id)
    }

    override suspend fun readTodos(): Flow<List<Todo>> {
        return dao.readTodos()
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

}