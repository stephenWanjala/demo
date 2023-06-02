package com.github.stephenWanjala.demo.core.util

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateRequest(
    val token:String
)

interface AuthenticateResponse