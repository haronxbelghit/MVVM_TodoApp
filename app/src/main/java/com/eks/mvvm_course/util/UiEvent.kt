package com.eks.mvvm_course.util

sealed class UiEvent {

    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackbar(val msg: String, val action: String?) : UiEvent()

}