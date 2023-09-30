package com.challenge.get.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.get.base.compose.extraLargePadding
import com.challenge.get.base.compose.largePadding
import com.challenge.get.base.compose.normalPadding

@Composable
fun SettingsScreen(
    username: String,
    onDeleteAccountClick: () -> Unit,
    onDeleteNotesClicked: () -> Unit,
    onImportClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = extraLargePadding,
                    vertical = extraLargePadding,
                )
                .align(Alignment.TopCenter),
            elevation = CardDefaults.cardElevation(
                defaultElevation = largePadding,
            ),
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
                    "Hi, $username",
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(vertical = normalPadding)
                        .align(Alignment.Start),
                )
                Spacer(modifier = Modifier.height(normalPadding))
                Divider()
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        "import json",
                    )
                    TextButton(
                        onClick = { onImportClick() },
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(text = "Import from JSON")
                    }
                }
                Divider()
                // Delete account
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.Close,
                        "delete account",
                    )
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Red,
                        ),
                        onClick = { onDeleteAccountClick() },
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(text = "Delete Account")
                    }
                }
                Divider()
                // Delete notes
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.Close,
                        "delete notes",
                    )
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Red,
                        ),
                        onClick = { onDeleteNotesClicked() },
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(text = "Delete Notes")
                    }
                }
                Divider()
                // Logout
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.ExitToApp,
                        "close session",
                    )
                    TextButton(
                        onClick = { onLogoutClick() },
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(text = "Logout")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        onDeleteAccountClick = {},
        onImportClick = {},
        username = "John Doe",
        onDeleteNotesClicked = {},
        onLogoutClick = {},
    )
}
