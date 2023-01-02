package com.app.learning.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.learning.InvalidPasswordException
import com.app.learning.InvalidUserDetailException
import com.app.learning.R
import com.app.learning.Result
import com.app.learning.view.ui.theme.LearningTheme
import com.app.learning.viewmodel.LearningViewmodel

@ExperimentalLifecycleComposeApi
@Composable
fun Login(
    navigateToRegistration: (Int) -> Unit,
    navigateToHome : () -> Unit,
    learningViewmodel: LearningViewmodel = viewModel()
) {
    var phoneNumberUserNameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isUserDetailValid by learningViewmodel.isUserDetailValid.collectAsStateWithLifecycle()

    when (isUserDetailValid) {
        is Result.Success -> {
            LaunchedEffect(key1 = "home"){
                navigateToHome()
            }
        }
        else -> {
            LoginContent(
                navigateToRegistration = navigateToRegistration,
                onValidateLogin = {
                    learningViewmodel.validatePhoneNumberUserNameOrEmailAndPassword(
                        phoneNumberUserNameOrEmail,
                        password
                    )
                },
                onPasswordValueChange = { password = it },
                onPhoneNumberUserNameOrEmailValueChange = { phoneNumberUserNameOrEmail = it },
                phoneNumberUserNameOrEmail = phoneNumberUserNameOrEmail,
                password = password,
                result = isUserDetailValid
            )
        }
    }
}

@Composable
private fun LoginContent(
    navigateToRegistration: (Int) -> Unit,
    onValidateLogin: () -> Unit,
    onPhoneNumberUserNameOrEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    phoneNumberUserNameOrEmail: String,
    password: String,
    result: Result<*>
) {
    when(result){
        is Result.Loading -> {
            ProgressDialog()
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "English (United Kingdom)",
            textAlign = TextAlign.Center
        )
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
            ErrorTextField(
                label = "Phone number,username,or email",
                phoneNumberUserNameOrEmail,
                onPhoneNumberUserNameOrEmailValueChange,
                error = when (result) {
                    is Result.Error -> when (result.exception) {
                        is InvalidUserDetailException -> result
                        else -> null
                    }
                    else -> null
                }
            )
            ErrorTextField(
                label = "Password",
                password,
                onPasswordValueChange,
                error = when (result) {
                    is Result.Error -> when (result.exception) {
                        is InvalidPasswordException -> result
                        else -> null
                    }
                    else -> null
                }
            )
            Button(
                onClick = onValidateLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Log In")
            }
            ClickableText(
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
                style = TextStyle(textAlign = TextAlign.Center),
                onClick = {}
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
            onClick = navigateToRegistration
        )
    }
}

@Composable
private fun ErrorTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: Result.Error? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            label = { Text(text = label) },
            trailingIcon = {
                error?.apply {
                    Icon(
                        Icons.Filled.Warning,
                        exception.message,
                        tint = MaterialTheme.colors.error
                    )
                }
            },
            singleLine = true,
            isError = error != null
        )
        error?.exception?.message?.apply {
            Text(
                text = this,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLoginPreview() {
    LearningTheme {
        LoginContent(
            navigateToRegistration = {},
            onValidateLogin = {},
            onPasswordValueChange = {},
            onPhoneNumberUserNameOrEmailValueChange = {},
            password = "Password",
            phoneNumberUserNameOrEmail = "Phone Number",
            result = Result.Empty
        )
    }
}