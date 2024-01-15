package com.javi.domain.mapping

import com.javi.data.dto.UserDto
import com.javi.domain.model.User

fun UserDto.toUser(): User {
    return User(
        name = this.name
    )
}