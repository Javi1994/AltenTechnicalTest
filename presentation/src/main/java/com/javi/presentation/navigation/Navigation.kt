package com.javi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable(route = Screen.ContactDetailScreen.route) {
            ContactDetailScreen(navController = navController)
        }
    }
}