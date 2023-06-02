package com.github.stephenWanjala.demo.signup

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.stephenWanjala.demo.core.presentation.components.LogoSection
import com.github.stephenWanjala.demo.signup.presentation.SignUpForm

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
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
            onSignUpClick = onSignUpClick
        )

    }

}