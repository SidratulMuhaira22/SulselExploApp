package com.sidratul.sulselexploapp.ui.navigation

sealed class PanelNav(val route: String) {
    object Home : PanelNav("home")
    object Favorite : PanelNav("favorite")
    object Profile : PanelNav("profile")
    object Detail : PanelNav("home/{tourId}") {
        fun createRoute(tourId: Int) = "home/$tourId"
    }
}