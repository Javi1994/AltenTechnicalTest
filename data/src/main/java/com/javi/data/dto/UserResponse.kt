package com.javi.data.dto

data class UserResponse(
    val info: Info,
    val results: List<UserDto>
)