import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import core.util.Screen
import home.presentation.HomeScreen
import login.LoginScreen
import signup.SignUpScreen


@Composable
@Preview
fun App(
    currentScreen: Screen = Screen.LOGIN,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    navigateToHome: () -> Unit

) {


    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {


            when (currentScreen) {
                Screen.LOGIN -> {
                    LoginScreen(
                        onSignUpClick = onSignUpClick,
                        onLoginClick = onLoginClick
                    )
                }

                Screen.SIGNUP -> {
                    SignUpScreen(
                        onLoginClick = onLoginClick,
                        onSignUpClick = navigateToHome
                    )
                }

                Screen.HOME -> {
                    HomeScreen()
                }
            }
        }
    }

}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        var currentScreen by remember {
            mutableStateOf(Screen.LOGIN)
        }
        window.title = currentScreen.name
        App(currentScreen = currentScreen,
            onSignUpClick = {
                currentScreen = Screen.SIGNUP
            },
            onLoginClick = {
                currentScreen = Screen.LOGIN
            },
            navigateToHome = {
                currentScreen = Screen.HOME
            })
    }
}
