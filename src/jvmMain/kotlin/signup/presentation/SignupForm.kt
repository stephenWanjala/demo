package signup.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.InputTextField
import core.presentation.components.PasswordTextField

@Composable
fun SignupForm(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            textValue = name.value,
            onValueChange = { name.value = it },
            labelText = "name"
        )
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
            onClick = { /* Handle signup logic here */
                onSignUpClick()
            },
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text("Signup")
        }

        Text(
            text = "Already have an account? Login",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onLoginClick() },
            textAlign = TextAlign.Center
        )


    }
}