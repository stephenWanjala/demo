package com.github.stephenWanjala.demo.signup.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val token: String
)
