package core.data.repositoryImpl

import domain.repository.AuthRepository
import io.ktor.client.*
import login.domain.model.LoginRequest
import login.domain.model.LoginResponse

class AuthRepositoryImpl(client: HttpClient) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun register(loginRequest: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}