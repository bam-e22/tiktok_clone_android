package com.example.tiktok.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tiktok.R
import com.example.tiktok.ui.utils.Sizes

@Composable
fun CircleAvatar(
    uid: String,
    name: String,
    modifier: Modifier = Modifier,
    drawBorder: Boolean = false
) {
    val avatarModifier = modifier
        .size(Sizes.LargeIconSize)
        .clip(CircleShape)

    if (drawBorder) {
        avatarModifier.then(
            Modifier
                .border(BorderStroke(1.dp, Color.White), CircleShape)
                .padding(1.dp)
        )
    }

    AsyncImage(
        contentScale = ContentScale.Crop,
        modifier = avatarModifier,
        model = "https://avatars.githubusercontent.com/u/23008504?s=400&u=d46c78c9cd10e3f3b196543cd0e641e551740a3b&v=4",
        contentDescription = stringResource(id = R.string.cd_avatar, name),
    )
}
