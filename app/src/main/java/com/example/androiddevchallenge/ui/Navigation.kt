package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ItemsList
import com.example.androiddevchallenge.NewsStory
import com.example.androiddevchallenge.ui.MainDestinations.DETAIL
import com.example.androiddevchallenge.ui.MainDestinations.DETAIL_ID_KEY

/**
 * Destinations used in the ([OwlApp]).
 */
object MainDestinations {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val COURSES_ROUTE = "courses"
    const val DETAIL_ID_KEY = "detailId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.HOME) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME) {
            ItemsList(Array(19) { "n = $it" }, actions.selectCourse)
        }
        composable(
            "$DETAIL/{$DETAIL_ID_KEY}",
            arguments = listOf(navArgument(DETAIL_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)

            NewsStory(
                detailId = arguments.getInt(DETAIL_ID_KEY)
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val selectCourse: (Int) -> Unit = { courseId: Int ->
        navController.navigate("$DETAIL/$courseId")
    }
}
