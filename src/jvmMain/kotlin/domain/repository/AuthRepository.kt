package domain.repository

import login.domain.model.LoginRequest
import login.domain.model.LoginResponse

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun register(loginRequest: LoginRequest): LoginResponse
}