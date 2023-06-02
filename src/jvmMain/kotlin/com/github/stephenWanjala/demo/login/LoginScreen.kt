package com.github.stephenWanjala.demo.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.stephenWanjala.demo.core.presentation.LoginEvent
import com.github.stephenWanjala.demo.core.presentation.components.LoadingDialog
import com.github.stephenWanjala.demo.core.presentation.components.LogoSection
import com.github.stephenWanjala.demo.login.components.LoginForm

@Composable
fun LoginScreen(
    onSignupClick: () -> Unit,
    onLoginClick: (email: String, password: String) -> Unit,

    ) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoSection()
                Spacer(modifier = Modifier.height(16.dp))
                LoginForm(
                    onSignupClick = onSignupClick,
                    onLoginClick = { onLoginClick(email.value, password.value) },
                    onLoginEmailChange = {
                        email.value = it
                    },
                    onLoginPassword = {
                        password.value = it.take(12)
                    },
                    loginButtonEnabled = loginButtonEnabled(email.value, password.value),
                    email = email.value,
                    password = password.value
                )
            }
        }
    }
}

fun loginButtonEnabled(email: String, password: String): Boolean {
    val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-zA-Z0-9._-]+")
    return email.isNotEmpty() && password.isNotEmpty()
            && emailRegex.matches(email)
            && password.length>8


}