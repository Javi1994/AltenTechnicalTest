package com.javi.presentation.components

import android.app.Activity
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBarColorComponent(
    lightBars: Boolean
) {
    val view = LocalView.current
    SideEffect {
        val activity  = view.context as Activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = lightBars
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightNavigationBars = lightBars
        }
    }
}