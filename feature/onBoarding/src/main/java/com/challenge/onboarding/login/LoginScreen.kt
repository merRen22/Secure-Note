package com.challenge.onboarding.login

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.challenge.get.base.R
import com.challenge.get.base.compose.CustomTextField
import com.challenge.get.base.compose.extraLargePadding
import com.challenge.get.base.compose.imageSize
import com.challenge.get.base.compose.largePadding
import com.challenge.get.base.compose.normalPadding

@Composable
fun LoginScreen(
    username: String,
    onLoginClick: (String) -> Unit,
    onBiometricAuth: (String) -> Unit,
    fragment: Fragment,
) {
    val passwordText = remember { mutableStateOf(String()) }

    val biometricPrompt = BiometricPrompt(
        fragment,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onBiometricAuth(errString.toString())
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onBiometricAuth("")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onBiometricAuth("Biometric authentication failed, try again")
            }
        },
    )

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
            LoginHeader()
            UserNameInput(
                username,
                passwordText,
                biometricPrompt,
            )
        }
        Box(
            modifier = Modifier
                .padding(
                    start = extraLargePadding,
                    end = extraLargePadding,
                    bottom = extraLargePadding,
                )
                .align(Alignment.BottomCenter),
        ) {
            LoginBottom(
                passwordText = passwordText,
                onLoginButtonClicked = onLoginClick,
            )
        }
    }
}

@Composable
fun LoginHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = normalPadding,
            ),
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(
                        height = imageSize,
                        width = imageSize,
                    ),
            )
        }
    }
}

@Composable
fun UserNameInput(
    username: String,
    passwordText: MutableState<String>,
    biometricPrompt: BiometricPrompt,
) {
    val context = LocalContext.current
    val biometricManager = remember { BiometricManager.from(context) }
    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
    }

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BIOMETRIC_WEAK)
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Use account password")
        .build()

    Column(
        modifier = Modifier.padding(vertical = largePadding),
    ) {
        Text(
            "Welcome back $username",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier
                .padding(vertical = normalPadding)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(normalPadding))
        Divider()
        Spacer(modifier = Modifier.height(normalPadding))
        CustomTextField(
            text = passwordText.value,
            placeholder = "Please enter your password to continue",
            onChange = {
                passwordText.value = it
            },
            isPassword = true,
        )
        if (isBiometricAvailable == 0) {
            TextButton(
                onClick = { biometricPrompt.authenticate(promptInfo) },
            ) {
                Text("Authenticate with Biometrics")
            }
        } else {
            Text(
                "Biometric authentication is not available on the device",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(vertical = normalPadding)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
fun LoginBottom(
    passwordText: MutableState<String>,
    onLoginButtonClicked: (String) -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onLoginButtonClicked(passwordText.value) },
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(text = "Iniciar sesion")
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        username = "",
        onLoginClick = {},
        onBiometricAuth = {},
        fragment = Fragment(),
    )
}
