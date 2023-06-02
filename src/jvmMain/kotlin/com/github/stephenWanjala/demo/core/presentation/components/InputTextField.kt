package com.github.stephenWanjala.demo.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String,
    maxLines: Int = 1,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSendAction: (() -> Unit?)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    readOnly: Boolean = false,
    placeHolder: String? = null

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val keyBoardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = textValue,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            label = { Text(text = labelText) },
            placeholder = {
                val holderText: String = placeHolder ?: labelText
                Text(text = holderText)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoardController?.hide()
                },
                onSend = {
                    keyBoardController?.hide()
                    onSendAction?.let { it() }
                }
            ),
            isError = isError,
            visualTransformation = visualTransformation,
            readOnly = readOnly,
            maxLines = maxLines,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.error,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurface
            )
        )

    }

}

