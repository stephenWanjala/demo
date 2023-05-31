package core.presentation.components





import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String = "password",
    maxLines: Int = 1,
    placeHolder: String = "Enter Password",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password,

        ),
    onSendAction: (() -> Unit?)? = null,
    isError: Boolean = false,
    allowedLimit: Int = 8,
    errorMessage: String? = null

) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val trailingIcon = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
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
            label = { Text(labelText) },
            placeholder = { Text(placeHolder) },
            isError = isError,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(
                '*'
            ),
            maxLines = maxLines,
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Password Visibility"
                    )
                }
            },
        )

    }

}
