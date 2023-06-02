package com.github.stephenWanjala.demo.core.data.repositoryImpl

import com.github.stephenWanjala.demo.core.util.AuthenticateRequest
import com.github.stephenWanjala.demo.core.util.Resource
import com.github.stephenWanjala.demo.domain.repository.AuthRepository
import com.github.stephenWanjala.demo.login.domain.model.LoginRequest
import com.github.stephenWanjala.demo.login.domain.model.LoginResponse
import com.github.stephenWanjala.demo.signup.domain.model.RegisterRequest
import com.github.stephenWanjala.demo.signup.domain.model.RegisterResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(private val client: HttpClient) : AuthRepository {
    private val baseUrl = "http://localhost:8080"
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {

        return flow {
            emit(Resource.Loading())
            try {
                val result = client.post<LoginResponse> {
                    url("$baseUrl/login")
                    contentType(ContentType.Application.Json)
                    body = loginRequest
                }
                emit(Resource.Success(result))
            } catch (e: Exception) {
                println(e.message)
                emit(Resource.Error(e))
            }

        }.catch {
            emit(Resource.Error(it))
            println(it.message)
        }

    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = client.post<RegisterResponse> {
                    url("$baseUrl/register")
                    contentType(ContentType.Application.Json)
                    body = registerRequest
                }
                emit(Resource.Success(result))
            } catch (e: Exception) {
                println(e.message)
                emit(Resource.Error(e))
            }
        }.catch {
            emit(Resource.Error(it))
            println(it.message)
        }
    }

    override suspend fun authenticate(token: String): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = client.post<Boolean> {
                    url("$baseUrl/authenticate")
                    contentType(ContentType.Application.Json)
                    body = AuthenticateRequest(token)
                }
                emit(Resource.Success(result))
            } catch (e: Exception) {
                println(e.message)
                emit(Resource.Error(e))
            }
        }.catch {
            emit(Resource.Error(it))
            println(it.message)

        }
    }
}