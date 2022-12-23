package com.app.learning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.learning.R
import com.app.learning.Result
import com.app.learning.view.ui.theme.LearningTheme
import com.app.learning.viewmodel.LoginViewmodel

@Composable
fun StatefulSplash(
    navigateToLogin: () -> Unit,
    loginViewmodel: LoginViewmodel = viewModel()
) {
    when (loginViewmodel.openMainScreenAfterDelay().collectAsState().value) {
        is Result.Success -> {
            navigateToLogin()
        }
        is Result.Loading -> {
            StatelessSplash()
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun StatelessSplash() {
    Column(modifier = Modifier.padding(24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.instgaram_glyph_icon),
                contentDescription = "",
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
            )
        }
        Text(
            text = "from",
            fontSize = 14.sp,
            letterSpacing = 2.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Facebook".toUpperCase(),
            fontSize = 16.sp,
            letterSpacing = 2.2.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF58529),
                        Color(0xFFFEDA77),
                        Color(0xFFDD2A7B),
                        Color(0xFF8134AF),
                        Color(0xFF515BD4)
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSplashPreview() {
    LearningTheme {
        StatelessSplash()
    }
}