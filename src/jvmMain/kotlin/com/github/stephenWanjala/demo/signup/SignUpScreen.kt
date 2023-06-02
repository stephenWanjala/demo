package com.github.stephenWanjala.demo.signup

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.stephenWanjala.demo.core.presentation.components.LogoSection
import com.github.stephenWanjala.demo.signup.presentation.SignUpForm

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: (name: String, email: String, password: String) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoSection()
        Spacer(modifier = Modifier.height(16.dp))
        SignUpForm(
            onLoginClick = onLoginClick,
            onSignUpClick = { onSignUpClick(name.value, email.value, password.value) },
            onNameChange = {
                name.value = it
            },
            onEmailChange = {
                email.value = it
            },
            onPasswordChange = {
                password.value = it.take(12)
            },
            signUpButtonEnabled = signUpButtonEnabled(email.value, password.value, name.value),
            name = name.value,
            email = email.value,
            password = password.value


        )

    }

}

private fun signUpButtonEnabled(email: String, password: String, name: String): Boolean {
    val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-zA-Z0-9._-]+")
    return email.isNotEmpty() &&
            emailRegex.matches(email)
            && name.isNotEmpty() && name.length > 4
            && password.isNotEmpty() && password.length > 8
}