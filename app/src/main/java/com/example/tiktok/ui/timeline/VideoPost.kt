package com.example.tiktok.ui.timeline

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.model.VideoItem
import com.example.tiktok.ui.components.CircleAvatar
import com.example.tiktok.ui.components.Comment
import com.example.tiktok.ui.components.CommentInputPanel
import com.example.tiktok.ui.components.SnsButton
import com.example.tiktok.ui.components.VideoPlayer
import com.example.tiktok.ui.theme.Grey200
import com.example.tiktok.ui.utils.Sizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPost(
    videoItem: VideoItem,
    modifier: Modifier = Modifier
) {
    var showSheet by remember { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (showSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(fraction = 0.7f),
            sheetState = modalBottomSheetState,
            scrimColor = Color.Transparent,
            dragHandle = null,
            onDismissRequest = {
                showSheet = false
            }
        ) {
            VideoComments(
                comments = emptyList(),
                onClose = {
                    showSheet = false
                }
            )
        }
    }

    Box(
        modifier = modifier
    ) {
        VideoPlayer()
        VideoInfo(
            videoItem = videoItem,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 20.dp)
        )
        VideoSnsButtons(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp),
            onCommentTap = {
                showSheet = true
            }
        )
    }
}

@Composable
private fun VideoInfo(
    videoItem: VideoItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "@${videoItem.creator}",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.description,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.tags
                .joinToString(separator = " ") {
                    "#$it"
                },
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
    }
}

@Composable
private fun VideoSnsButtons(
    modifier: Modifier = Modifier,
    onCommentTap: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleAvatar(
            uid = "",
            name = "",
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            imageVector = Icons.Filled.Favorite,
            text = "0",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            painter = painterResource(id = R.drawable.comment_dots_solid),
            text = "0",
            onClick = onCommentTap
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            painter = painterResource(id = R.drawable.share_solid),
            text = "Share",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun VideoComments(
    comments: List<String>,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 20.dp),
                ) {
                    Text(
                        "댓글 ${comments.size}개",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = onClose
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            modifier = Modifier.size(Sizes.ExtraSmallIconSize),
                            contentDescription = stringResource(id = R.string.cd_close)
                        )
                    }
                }
            }
            items(8) {
                Comment(
                    creatorUid = "",
                    creatorName = "Meggin",
                    comment = "Put the pickpocket sound over this!!!",
                    createdAt = 1409392,
                    likes = 625000,
                    dislikes = 0,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Grey200
        )
        CommentInputPanel(
            creatorUid = "",
        )
    }
}
