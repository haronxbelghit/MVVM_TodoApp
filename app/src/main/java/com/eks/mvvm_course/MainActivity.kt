package com.eks.mvvm_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eks.mvvm_course.ui.theme.MVVM_CourseTheme
import com.eks.mvvm_course.ui.view.AddEditTodoScreen
import com.eks.mvvm_course.ui.view.TodoListScreen
import com.eks.mvvm_course.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // for hilt to understand
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_CourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVM_CourseTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.TODO_LIST
        ) {
            composable(Routes.TODO_LIST) {
                TodoListScreen(
                    onNavigate = {
                        navController.navigate(it.route)
                    }
                )
            }
            composable(
                route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                arguments = listOf(
                    navArgument(
                        name = "todoId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                AddEditTodoScreen(
                    onPopBackStack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}