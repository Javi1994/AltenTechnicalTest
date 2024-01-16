package com.javi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.javi.domain.model.User
import com.javi.presentation.contact_detail.ContactDetailScreen
import com.javi.presentation.contact_list.ContactListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ContactListScreen.route
    ) {
        composable(route = Screen.ContactListScreen.route) {
            ContactListScreen(navController = navController)
        }
        composable(
            route = "${Screen.ContactDetailScreen.route}/{$USER_ID_PARAM}",
            arguments = listOf(
                navArgument(USER_ID_PARAM) {
                    type = NavType.StringType
                }
            )
        ) {
            ContactDetailScreen(navController = navController)
        }
    }
}