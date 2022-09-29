package com.eks.mvvm_course.di

import android.app.Application
import androidx.room.Room
import com.eks.mvvm_course.model.TodoDatabase
import com.eks.mvvm_course.model.TodoRepository
import com.eks.mvvm_course.model.TodoRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
    Defines the dependencies we need through out the app + their lifecycle
    We don't need singletons that live through the whole app lifecycle
    Having some dependencies for a specific activity, the module only keeps them alive for that single activity

 */

@Module // Tells hilt this is a module
@InstallIn(SingletonComponent::class)
object AppModule {

    // we define how the dependency should be created and Hilt takes it from there, no need to call these functions.

    @Provides
    @Singleton
    // We're telling Hilt how to create our Database
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo db"
        ).build()
    }

    @Provides
    @Singleton
    // We're telling Hilt how to create our Repository
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImplementation(db.dao)
    }
}