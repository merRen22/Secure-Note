package com.challenge.onboarding.createUser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge.get.base.compose.CustomTextField
import com.challenge.get.base.compose.extraLargePadding
import com.challenge.get.base.compose.largePadding
import com.challenge.get.base.compose.normalPadding

@Composable
fun CreateUserScreen(
    onRegisterClick: (String, String) -> Unit,
) {
    val usernameText = remember { mutableStateOf(String()) }
    val passwordText = remember { mutableStateOf(String()) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = extraLargePadding,
                    vertical = extraLargePadding,
                ),
        ) {
            Text(
                "Welcome to Secure Notes!",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                modifier = Modifier
                    .padding(vertical = normalPadding),
            )
            Text(
                "Create your new account!",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                modifier = Modifier
                    .padding(vertical = normalPadding),
            )
            Divider()
            Spacer(modifier = Modifier.height(normalPadding))
            CustomTextField(
                text = usernameText.value,
                placeholder = "Username",
                onChange = {
                    usernameText.value = it
                },
            )
            Spacer(modifier = Modifier.height(normalPadding))
            CustomTextField(
                text = passwordText.value,
                placeholder = "Password",
                onChange = {
                    passwordText.value = it
                },
                isPassword = true,
            )
            Spacer(modifier = Modifier.height(largePadding))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onRegisterClick(usernameText.value, passwordText.value) },
                shape = RoundedCornerShape(20.dp),
            ) {
                Text(text = "Crear cuenta")
            }
            Spacer(modifier = Modifier.height(largePadding))
            Divider()
            Text(
                "On your next login you can use your biometric",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(vertical = normalPadding)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}
