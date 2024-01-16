package com.javi.domain.mapping

import com.javi.data.dto.UserDto
import com.javi.domain.model.User

fun UserDto.toUser(): User {
    return User(
        name = this.name.first + " " + this.name.last,
        email = this.email,
        image = this.picture.medium,
        id = this.id.value
    )
}