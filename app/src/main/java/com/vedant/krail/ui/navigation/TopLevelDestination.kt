package com.vedant.krail.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.vedant.krail.R

sealed class TopLevelDestination(
    val title: String,
    val route: String,
    val selectedItem: ImageVector,
    val unselectedIcon: ImageVector,) {
    data object Home : TopLevelDestination(
        title = "Home",
        route = "home",
        selectedItem = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object TrainDetails : TopLevelDestination(
        title = "TrainDetails",
        route = "train_details",
        selectedItem = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object Search : TopLevelDestination(
        title = "Search",
        route = "search",
        selectedItem = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search
    )

    data object Stations : TopLevelDestination(
        title = "Stations",
        route = "stations",
        selectedItem = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List
    )



    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
val bottomNavItems =
    listOf(TopLevelDestination.Home, TopLevelDestination.Search, TopLevelDestination.Stations)
