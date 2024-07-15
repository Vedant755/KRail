package com.vedant.krail.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vedant.krail.ui.homeScreen.HomeScreen
import com.vedant.krail.ui.homeScreen.HomeScreenViewModel
import com.vedant.krail.ui.searchScreen.SearchScreen
import com.vedant.krail.ui.stationsScreen.StationsScreen
import com.vedant.krail.ui.trainDetailScreen.TrainDetailsScreen

@Composable
fun AppNavigation(
    modifier:Modifier= Modifier,
    navController: NavHostController,
) {
    NavHost(modifier = modifier, navController = navController, startDestination = TopLevelDestination.Home.route){
        composable(route = TopLevelDestination.Search.route) { backStackEntry ->
            SearchScreen()
        }

        composable(route = TopLevelDestination.Stations.route) { backStackEntry ->
            StationsScreen()
        }
        composable(route = TopLevelDestination.Home.route) { backStackEntry ->
            HomeScreen(
                onClick = { trainNumber ->
                    navController.navigate(TopLevelDestination.TrainDetails.withArgs(trainNumber))
                }
            )
        }

        composable(
            route = TopLevelDestination.TrainDetails.route + "/{trainNumber}",
            arguments = listOf(navArgument("trainNumber") { type = NavType.StringType })

        ) { backStackEntry ->
            val trainNumber = backStackEntry.arguments?.getString("trainNumber") ?: ""
            TrainDetailsScreen(trainNumber = trainNumber)
        }


    }
}