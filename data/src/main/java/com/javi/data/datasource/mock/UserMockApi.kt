package com.javi.data.datasource.mock

import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.UserDto

class UserMockApi: UserApi {
    override suspend fun getUser(): UserDto {
        return UserDto()
    }
}