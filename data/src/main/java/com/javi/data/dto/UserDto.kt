package com.javi.data.dto

//TODO: Check every attribute because api sometimes returns null values
data class UserDto(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id?,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)