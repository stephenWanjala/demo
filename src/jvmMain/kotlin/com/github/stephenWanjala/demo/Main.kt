package com.github.stephenWanjala.demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.stephenWanjala.demo.core.util.Screen
import com.github.stephenWanjala.demo.home.presentation.HomeScreen
import com.github.stephenWanjala.demo.login.LoginScreen
import com.github.stephenWanjala.demo.signup.SignUpScreen
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.ktor.client.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException


@Composable
@Preview
fun App(
    currentScreen: Screen = Screen.LOGIN,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    navigateToHome: () -> Unit,
    token: String?

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
                        onLoginClick = navigateToHome
                    )
                }

                Screen.SIGNUP -> {
                    SignUpScreen(
                        onLoginClick = onLoginClick,
                        onSignUpClick = navigateToHome
                    )
                }

                Screen.HOME -> {
                    HomeScreen(token = token)
                }
            }
        }
    }

}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val windowScope = rememberWindowState()
        val isLoggedIn = remember { mutableStateOf(false) }
        val token = remember { mutableStateOf("") }


        var currentScreen by remember {
            mutableStateOf(Screen.LOGIN)
        }
        LaunchedEffect(isLoggedIn) {
            if (isLoggedIn.value) {
                currentScreen = Screen.HOME
            }
        }
        window.title = currentScreen.name
        App(
            currentScreen = currentScreen,
            onSignUpClick = {
                currentScreen = Screen.SIGNUP
            },
            onLoginClick = {
                currentScreen = Screen.LOGIN
            },
            navigateToHome = {
                currentScreen = Screen.HOME
            },
            token = token.value
        )
    }
}


suspend fun storeTokenToFile(token: String) {
    withContext(Dispatchers.IO) {
        val hiddenDirectory = File(System.getProperty("user.home"), ".myapp")
        hiddenDirectory.mkdir() // Create the hidden directory if it doesn't exist

        val tokenFile = File(hiddenDirectory, "token.txt")
        try {
            tokenFile.bufferedWriter().use { writer ->
                writer.write(token)
            }
        } catch (e: IOException) {
            // Handle file writing error
            println(e.message)
            return@withContext
        }
    }
}


suspend fun retrieveTokenFromFile(): String? {
    return withContext(Dispatchers.IO) {
        val hiddenDirectory = File(System.getProperty("user.home"), ".myapp")
        val tokenFile = File(hiddenDirectory, "token.txt")

        try {
            tokenFile.bufferedReader().use { reader ->
                return@withContext reader.readText()
            }
        } catch (e: FileNotFoundException) {
            // Handle file not found error
            println(e.message)
            return@withContext null
        } catch (e: IOException) {
            // Handle file reading error
            println(e.message)
            return@withContext null
        }
    }
}
//    val claims = decodeJwt(token)

fun decodeJwt(token: String, secrete: String = System.getenv("JWT_SECRET")): Claims = Jwts.parserBuilder()
    .setSigningKey(secrete) // Replace with your actual secret key
    .build()
    .parseClaimsJws(token)
    .body





//fun provideClient():HttpClient{
//
//}