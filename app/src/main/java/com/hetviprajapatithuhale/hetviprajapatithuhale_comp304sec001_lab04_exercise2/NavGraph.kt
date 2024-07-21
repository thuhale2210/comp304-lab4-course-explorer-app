package com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hetviprajapatithuhale.hetviprajapatithuhale_comp304sec001_lab04_exercise2.data.programList

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "welcome"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("welcome") {
            WelcomeScreen(
                onEnterClicked = { navController.navigate("programList") }
            )
        }
        composable("programList") {
            ProgramListScreen(
                programs = programList,
                onProgramSelected = { program ->
                    navController.navigate("courseList/${program.name}")
                }
            )
        }
        composable("courseList/{programName}") { backStackEntry ->
            val programName = backStackEntry.arguments?.getString("programName")
            val program = programList.find { it.name == programName }
            CourseListScreen(program = program, onBack = { navController.popBackStack() })
        }
    }
}
