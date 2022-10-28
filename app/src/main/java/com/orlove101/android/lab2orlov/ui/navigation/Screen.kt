package com.orlove101.android.lab2orlov.ui.navigation

enum class Screen(val route: Route) {
    Operations("operations"),
    Result("result");

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("?result=$arg")
            }
        }
    }

    companion object {
        fun fromRouteOrNull(route: Route?): Screen? =
            values().firstOrNull {
                it.route == route?.substringBefore("/") ||
                        it.route == route?.substringBefore("?")
            }
    }
}

typealias Route = String