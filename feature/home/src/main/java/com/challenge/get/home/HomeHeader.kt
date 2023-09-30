package com.challenge.get.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.get.base.compose.CustomTextField
import com.challenge.get.base.compose.extraLargeSize
import com.challenge.get.base.compose.largeIconSize
import com.challenge.get.base.compose.normalPadding
import com.challenge.get.base.compose.smallPadding

@Composable
fun HomeHeader(
    onSearchClick: (String) -> Unit,
    onSettingsClick: () -> Unit,
) {
    val searchText = remember { mutableStateOf(String()) }

    Card(
        modifier = Modifier.padding(normalPadding),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(0.8f).padding(normalPadding),
                text = searchText.value,
                placeholder = "Search by title",
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "searchIcon",
                        modifier = Modifier.padding(smallPadding).size(largeIconSize),
                    )
                },
                onChange = {
                    searchText.value = it
                    onSearchClick(it)
                },
            )
            IconButton(
                modifier = Modifier.padding(normalPadding).size(extraLargeSize),
                onClick = { onSettingsClick() },
            ) {
                Icon(
                    Icons.Filled.Settings,
                    "settings",
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader(
        onSearchClick = {},
        onSettingsClick = {},
    )
}
