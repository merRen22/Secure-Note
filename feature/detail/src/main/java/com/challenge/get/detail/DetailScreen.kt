package com.challenge.get.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.challenge.get.base.compose.CustomTextField
import com.challenge.get.base.compose.ProgressIndicator
import com.challenge.get.base.compose.extraLargePadding
import com.challenge.get.base.compose.normalPadding
import com.challenge.get.base.compose.smallPadding

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel,
    onRegisterClick: () -> Unit,
) {
    val isLoading = detailViewModel.serviceIsLoading.observeAsState()

    if (isLoading.value == true) {
        ProgressIndicator()
    } else {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = extraLargePadding,
                    vertical = extraLargePadding,
                )
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .systemBarsPadding(),
        ) {
            AsyncImage(
                model = detailViewModel.image.value.ifEmpty { "https://picsum.photos/800/600" },
                contentDescription = "note_image",
                modifier = Modifier.fillMaxWidth().padding(vertical = normalPadding),
            )
            TextButton(
                onClick = {
                    detailViewModel.image.value = "https://picsum.photos/800/600"
                },
            ) {
                Text(text = "Copy link to field")
            }
            Spacer(modifier = Modifier.height(smallPadding))
            CustomTextField(
                text = detailViewModel.title.value,
                placeholder = "Title",
                onChange = {
                    detailViewModel.title.value = it
                },
            )
            Spacer(modifier = Modifier.height(smallPadding))
            CustomTextField(
                text = detailViewModel.description.value,
                placeholder = "Description",
                onChange = {
                    detailViewModel.description.value = it
                },
            )
            Spacer(modifier = Modifier.height(smallPadding))
            CustomTextField(
                text = detailViewModel.image.value,
                placeholder = "Image url",
                onChange = {
                    detailViewModel.image.value = it
                },
            )
            Spacer(modifier = Modifier.height(extraLargePadding))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onRegisterClick() },
                shape = RoundedCornerShape(20.dp),
            ) {
                Text(
                    text = if (detailViewModel.noteid == 0) {
                        "Create note"
                    } else {
                        "Update note"
                    },
                )
            }
        }
    }
}
