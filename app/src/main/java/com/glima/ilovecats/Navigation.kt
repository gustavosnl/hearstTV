package com.glima.ilovecats

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.glima.ilovecats.feature.detail.BreedDetailScreen
import com.glima.ilovecats.feature.list.BreedListScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.BreedList.route) {
        composable(route = Screen.BreedList.route) { BreedListScreen(navController = navController) }
        composable(
            route = Screen.BreedDetail.route,
            arguments = listOf(navArgument("breed") { })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("breed")?.let {
                BreedDetailScreen(breed = it, navController = navController)
            }
        }
    }
}


sealed class Screen(val route: String, @StringRes val title: Int) {
    object BreedList : Screen("breed_list", R.string.breed_list_title)
    object BreedDetail : Screen("breed_detail/{breed}", R.string.breed_detail_title)
}
