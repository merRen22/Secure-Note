package com.challenge.get.base.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    multiline: Boolean = false,
    isPassword: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 14.sp),
        enabled = isEnabled,
        singleLine = !multiline,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            )
        },
    )
}
