import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

enum class Screen {
    LOGIN, SIGNUP, HOME
}

@Composable
@Preview
fun App() {
    var currentScreen by remember {
        mutableStateOf(Screen.LOGIN)
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (currentScreen) {
                    Screen.LOGIN -> {
                        LoginForm(
                            onSignupClick = { currentScreen = Screen.SIGNUP }
                        )
                    }

                    Screen.SIGNUP -> {
                        SignupForm(
                            onLoginClick = { currentScreen = Screen.LOGIN }
                        )
                    }

                    Screen.HOME -> TODO()
                }
            }
        }
    }
}


@Composable
fun LoginForm(onSignupClick: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        InputTextField(email)
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = { /* Handle login logic here */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Login")
        }
        Text(
            text = "Don't have an account? Sign up",
            color = Color.Blue,
            modifier = Modifier.clickable { onSignupClick() }
        )
    }
}

@Composable
fun SignupForm(onLoginClick: () -> Unit) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") }
        )
        InputTextField(email)
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = { /* Handle signup logic here */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Signup")
        }
        Text(
            text = "Already have an account? Login",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onLoginClick() }
        )
    }
}

@Composable
private fun InputTextField(
    text: MutableState<String>,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Email") },
        modifier = modifier
    )
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
