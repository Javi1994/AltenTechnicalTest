package com.javi.data

import com.javi.data.dto.Coordinates
import com.javi.data.dto.Dob
import com.javi.data.dto.Id
import com.javi.data.dto.Location
import com.javi.data.dto.Login
import com.javi.data.dto.Name
import com.javi.data.dto.Picture
import com.javi.data.dto.Registered
import com.javi.data.dto.Street
import com.javi.data.dto.Timezone
import com.javi.data.dto.UserDto

object FakeUserData {
    val validUserDto = UserDto(
        gender = "female",
        name = Name(
            title = "Javi",
            first = "Caselles",
            last = "Cabrera"
        ),
        location = Location(
            street = Street(name = "", number = 0),
            city = "",
            state = "",
            country = "",
            postcode = "",
            coordinates = Coordinates(
                latitude = "", longitude = ""
            ),
            timezone = Timezone(
                description = "",
                offset = ""
            )
        ),
        email = "javi@example.com",
        login = Login(
            uuid = "7b4c522d-0738-4a74-83dd-efa318c2c570",
            username = "ticklishtiger964",
            password = "",
            salt = "",
            md5 = "",
            sha1 = "",
            sha256 = "",
        ),
        dob = Dob(age = 28, date = ""),
        registered = Registered(
            age = 28,
            date = "2006-10-09T14:35:05.258Z"
        ),
        phone = "041-717-3407",
        cell = "",
        id = null,
        picture = Picture(large = "", medium = "", thumbnail = ""),
        nat = "IE"
    )

    val validUsers = listOf(validUserDto, validUserDto, validUserDto, validUserDto, validUserDto)
}