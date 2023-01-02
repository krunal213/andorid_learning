package com.app.learning.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.learning.view.ui.theme.LearningTheme
import com.app.learning.R

@Composable
fun Feed() {
    FeedContent()
}

@Composable
private fun FeedContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = "Instagram",
                        fontFamily = FontFamily(Font(R.font.billabong, FontWeight.Bold)),
                        fontSize = 32.sp
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Outlined.MailOutline, contentDescription = null)
                    }
                },
                elevation = 1.dp
            )
        },
        content = {
            it
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultFeedPreview() {
    LearningTheme {
        Feed()
    }
}