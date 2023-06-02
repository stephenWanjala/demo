package com.github.stephenWanjala.demo.domain.repository

import com.github.stephenWanjala.demo.core.util.Resource
import com.github.stephenWanjala.demo.login.domain.model.LoginRequest
import com.github.stephenWanjala.demo.login.domain.model.LoginResponse
import com.github.stephenWanjala.demo.signup.domain.model.RegisterRequest
import com.github.stephenWanjala.demo.signup.domain.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>

    suspend fun register(registerRequest: RegisterRequest):Flow<Resource<RegisterResponse>>

    suspend fun authenticate(token: String): Flow<Resource<Boolean>>
}