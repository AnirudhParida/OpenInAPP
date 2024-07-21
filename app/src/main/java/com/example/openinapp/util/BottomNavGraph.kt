package com.example.openinapp.util

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.openinapp.ui.screens.CampaignScreen
import com.example.openinapp.ui.screens.CoursesScreen
import com.example.openinapp.ui.dashboard.LinksScreen
import com.example.openinapp.ui.screens.ProfileScreen

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues, application:Application) {
    NavHost(
        navController = navController,
        startDestination = Screens.Links.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Screens.Links.route) {
            LinksScreen(application = application)
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen()
        }

        composable(route = Screens.Courses.route) {
            CoursesScreen()
        }
        composable(route = Screens.Campaign.route) {
            CampaignScreen()
        }
    }
}