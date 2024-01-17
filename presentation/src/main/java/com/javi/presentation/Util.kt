package com.javi.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.javi.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Utility method for observing a flow with lifecycle in a composable
 */
@Composable
fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect { event ->
                withContext(Dispatchers.Main.immediate) {
                    onEvent(event)
                }
            }
        }
    }
}

fun formatUserDate(date: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    val dateToFormat = SimpleDateFormat("dd-MM-yyyy")
    return try {
        val date: Date = format.parse(date)
        val dateTime: String = dateToFormat.format(date)
        dateTime
    } catch (e: ParseException) {
        e.printStackTrace()
        "Undefined Date"
    }
}

@Composable
fun getGenderString(gender: String): String {
    return when (gender) {
        User.MALE -> {
            stringResource(id = R.string.contact_male)
        }

        User.FEMALE -> {
            stringResource(id = R.string.contact_female)
        }

        else -> {
            ""
        }
    }
}