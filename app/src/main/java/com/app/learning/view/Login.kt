package com.app.learning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.learning.R
import com.app.learning.view.ui.theme.LearningTheme

@Composable
fun StatefulLogin(navigateToRegistration : (Int) -> Unit) {
    StatelessLogin(navigateToRegistration = navigateToRegistration)
}

@Composable
private fun StatelessLogin(navigateToRegistration: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(16.dp), text = "English (United Kingdom)", textAlign = TextAlign.Center)
        Column(
            Modifier
                .padding(start = 24.dp, end = 24.dp)
                .weight(1f, true),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.instagram_text_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally)
            )
            TextField(
                value = "Phone number,username,or email",
                onValueChange = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            TextField(
                value = "Password",
                onValueChange = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally), onClick = { /*TODO*/ }) {
                Text(text = "Log In")
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp),
                text = buildAnnotatedString {
                    append("Forgotten your login details? ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Get help with signing in.")
                    }
                },
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
        Divider()
        ClickableText(
            text = buildAnnotatedString {
                append("Don't have an account? ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Sign up.")
                }
            },
            modifier = Modifier.padding(16.dp),
            onClick = {
                navigateToRegistration(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLoginPreview() {
    LearningTheme {
        StatelessLogin{}
    }
}