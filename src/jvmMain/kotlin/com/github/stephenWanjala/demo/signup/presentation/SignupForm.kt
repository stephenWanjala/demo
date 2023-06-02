package com.github.stephenWanjala.demo.signup.presentation

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
import com.github.stephenWanjala.demo.core.presentation.components.InputTextField
import com.github.stephenWanjala.demo.core.presentation.components.PasswordTextField

@Composable
fun SignUpForm(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    name:String,
    email:String,
    password:String,
    onNameChange:(String)->Unit,
    onEmailChange:(String)->Unit,
    onPasswordChange:(String)->Unit,
    signUpButtonEnabled:Boolean
) {


    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            textValue = name,
            onValueChange = onNameChange,
            labelText = "Name"
        )
        InputTextField(
            textValue = email,
            onValueChange = onEmailChange,
            labelText = "Email"
        )
        PasswordTextField(
            textValue = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            placeHolder = "Password"
        )
        Button(
            onClick = { /* Handle signup logic here */
                onSignUpClick()
            },
            modifier = Modifier.padding(end = 16.dp),
            enabled = signUpButtonEnabled
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

