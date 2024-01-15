package com.javi.presentation.navigation

sealed class Screen(val route: String) {
    data object ContactListScreen : Screen("contact_list_screen")
    data object ContactDetailScreen : Screen("contact_detail_screen")

}