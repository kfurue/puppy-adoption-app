/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
