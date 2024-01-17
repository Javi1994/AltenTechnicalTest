package com.javi.domain.model

data class User(
    val name: String,
    val email: String,
    val image: String,
    val id: String,
    val gender: String,
    val registerDate: String,
    val phone: String,
    val location: String
) {
    companion object {

        const val MALE = "male"
        const val FEMALE = "female"

        fun sampleUser(): User {
            return User(
                "Name",
                "Email",
                "Image",
                "Id",
                "Gender",
                "RegisterDate",
                "Phone",
                "Location",
            )
        }
    }
}