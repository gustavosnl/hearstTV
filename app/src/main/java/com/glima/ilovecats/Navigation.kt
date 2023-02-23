package com.glima.ilovecats

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.glima.ilovecats.feature.detail.BreedDetailScreen
import com.glima.ilovecats.feature.list.BreedListScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "breed_list") {
        composable(route = "breed_list") { BreedListScreen(navController = navController) }
        composable(
            route = "breed_detail/{breed}",
            arguments = listOf(navArgument("breed") { })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("breed")?.let {
                BreedDetailScreen(breed = it)
            }
        }
    }
}
