package core.data.repositoryImpl

import domain.repository.AuthRepository
import io.ktor.client.*

class AuthRepositoryImpl(client: HttpClient) : AuthRepository