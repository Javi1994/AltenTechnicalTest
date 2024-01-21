package com.javi.data

import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.Coordinates
import com.javi.data.dto.Dob
import com.javi.data.dto.Info
import com.javi.data.dto.Location
import com.javi.data.dto.Login
import com.javi.data.dto.Name
import com.javi.data.dto.Picture
import com.javi.data.dto.Registered
import com.javi.data.dto.Street
import com.javi.data.dto.Timezone
import com.javi.data.dto.UserDto
import com.javi.data.dto.UserResponse

class FakeUserApi : UserApi {

    private var throwError: Exception? = null

    override suspend fun getUser(): UserResponse {
        throwError?.let {
            throw it
        }
        return generateRandomUser()
    }

    private val uuiddList = listOf(
        "7b4c522d-0738-4a74-83dd-efa318c2c570",
        "81da23df-0738-4a74-83dd-efa318c2c570",
        "123va4asc-0738-4a74-83dd-efa318c2c570",
        "92asc232-0738-4a74-83dd-efa318c2c570",
        "219ds81a-0738-4a74-83dd-efa318c2c570",
        "sd1a1da-0738-4a74-83dd-efa318c2c570",
        "12adca13-0738-4a74-83dd-efa318c2c570",
        "124ad1a1-0738-4a74-83dd-efa318c2c570",
        "591kasd1-0738-4a74-83dd-efa318c2c570",
        "541d19sa-0738-4a74-83dd-efa318c2c570",
    )

    private fun generateRandomUser(): UserResponse {
        return UserResponse(
            info = Info(1, 1, "", ""),
            results = listOf(
                UserDto(
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
                        uuid = uuiddList.random(),
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
            )
        )
    }

    fun shouldThrowError(throwError: Exception?) {
        this.throwError = throwError
    }
}