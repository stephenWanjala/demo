package com.github.stephenWanjala.demo.core.presentation.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.LoadingDialog() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp)
            .align(Alignment.Center),
        color = MaterialTheme.colorScheme.primary
    )
}