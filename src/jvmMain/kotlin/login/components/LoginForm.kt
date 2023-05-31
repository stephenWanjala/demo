package login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.InputTextField
import core.presentation.components.PasswordTextField

@Composable
fun LoginForm(
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            textValue = email.value,
            onValueChange = { email.value = it },
            labelText = "email"
        )
        PasswordTextField(
            textValue = password.value,
            onValueChange = { password.value = it },
            labelText = "password",
            placeHolder = "password"

        )

        Button(
            onClick = onLoginClick,
            modifier = Modifier
        ) {
            Text("Login")
        }

        Text(
            text = "Don't have an account? Sign up",
            color = Color.Blue,
            modifier = Modifier.clickable { onSignupClick() },
            textAlign = TextAlign.Center
        )

    }
}