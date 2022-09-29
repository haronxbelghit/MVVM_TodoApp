package com.eks.mvvm_course

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    Setting up Hilt for dependency injection
 */
@HiltAndroidApp
class TodoApp : Application()