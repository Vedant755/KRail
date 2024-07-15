package com.vedant.krail.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vedant.krail.ui.theme.AppTheme
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.PratikFagadiya.smoothanimationbottombar.ui.theme.BlueTint
import com.vedant.krail.R
import com.vedant.krail.ui.navigation.AppNavigation
import com.vedant.krail.ui.navigation.TopLevelDestination
import com.vedant.krail.ui.navigation.bottomNavItems

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val currentEntry by navController.currentBackStackEntryAsState()
        val currentIndex = rememberSaveable { mutableIntStateOf(0) }

        val shouldShowBottomBar = remember(currentEntry) {
            bottomNavItems.any { it.route == (currentEntry?.destination?.route) }
        }

        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = shouldShowBottomBar,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    val bottomNavigationItems = listOf(
                        SmoothAnimationBottomBarScreens(
                            TopLevelDestination.Home.route,
                            "Home",
                            com.PratikFagadiya.smoothanimationbottombar.R.drawable.baseline_home_24,
                        ),
                        SmoothAnimationBottomBarScreens(
                             TopLevelDestination.Search.route,
                             "Search",
                             TopLevelDestination.Search.selectedItem,

                        ),
                        SmoothAnimationBottomBarScreens(
                             TopLevelDestination.Stations.route,
                             "Stations",
                             TopLevelDestination.Stations.selectedItem,
                        )
                    )

                    SmoothAnimationBottomBar(
                        navController = navController,
                        bottomNavigationItems = bottomNavigationItems,
                        initialIndex = currentIndex,
                        bottomBarProperties = BottomBarProperties(),
                        onSelectItem = { item ->
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            currentIndex.value = bottomNavigationItems.indexOf(item)
                            Log.i("SELECTED_ITEM", "Selected Item: ${item.name}")
                        }
                    )
                }
            }
        ) { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}




