package com.orlove101.android.lab2orlov.ui.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orlove101.android.lab2orlov.ui.operation.OperationsScreen
import com.orlove101.android.lab2orlov.ui.result.ResultScreen

@Composable
fun MitNavHost(startDestination: Route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        Screen.values().forEach { screen ->
            composable(
                route = screen.route + when (screen) {
                    Screen.Operations -> ""
                    Screen.Result -> "?result={result}"
                },
                arguments = mutableListOf<NamedNavArgument>().apply {
                    when (screen) {
                        Screen.Result ->
                            add(
                                navArgument("result") {
                                    type = NavType.StringType
                                    nullable = true
                                }
                            )
                        else -> Log.d(TAG, "MitNavHost: wrong screen!")
                    }
                }
            ) { Contents(it, navController) }
        }
    }
}

@Composable
fun Contents(route: NavBackStackEntry, navController: NavController) {
    EnterAnimation {
        when (
            Screen.fromRouteOrNull(route.destination.route)
        ) {
            Screen.Operations -> OperationsScreen(navController = navController)
            Screen.Result -> ResultScreen(
                navController = navController,
                result = route.arguments?.getString("result").orEmpty()
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    // it would be cool if You recommend some other way to animate transitions in compose
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = slideInVertically(),
        exit = slideOutVertically(),
        content = content
    )
}

private const val TAG = "NavHost"