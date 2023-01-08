package com.app.learning.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.learning.view.ui.theme.LearningTheme

@Composable
fun GithubActions() {
    GithubActionsContent()
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun GithubActionsContent() {
    Column(modifier = Modifier.padding(24.dp).verticalScroll(rememberScrollState())) {
        Text(text = "name: Android Publish\n" +
                "\n" +
                "on:\n" +
                "  push:\n" +
                "    branches: [ master ]\n" +
                "\n" +
                "jobs:\n" +
                "  build:\n" +
                "\n" +
                "    runs-on: ubuntu-latest\n" +
                "\n" +
                "    steps:\n" +
                "      - uses: actions/checkout@v1\n" +
                "\n" +
                "      - name: Set Up JDK\n" +
                "        uses: actions/setup-java@v1\n" +
                "        with:\n" +
                "          java-version: 11\n" +
                "\n" +
                "      - name: Change wrapper permissions\n" +
                "        run: chmod +x ./gradlew\n" +
                "\n" +
                "      - name: Run Tests\n" +
                "        run: ./gradlew test\n" +
                "\n" +
                "      - name: Build Project\n" +
                "        run: ./gradlew build\n" +
                "\n" +
                "      - name: Build Release AAB\n" +
                "        run: ./gradlew bundleRelease\n" +
                "\n" +
                "      - name: Sign AAB\n" +
                "        uses: r0adkll/sign-android-release@v1\n" +
                "        with:\n" +
                "          releaseDirectory: app/build/outputs/bundle/release\n" +
                "          signingKeyBase64: ${{ "secrets.SIGNING_KEY" }}\n" +
                "          alias: ${{ "secrets.ALIAS" }}\n" +
                "          keyStorePassword: ${{ "secrets.KEY_STORE_PASSWORD" }}\n" +
                "          keyPassword: ${{ "secrets.KEY_PASSWORD" }}\n" +
                "\n" +
                "      - name: Deploy to Play Store\n" +
                "        uses: r0adkll/upload-google-play@v1.0.19\n" +
                "        with:\n" +
                "          serviceAccountJsonPlainText: ${{ "secrets.SERVICE_ACCOUNT_JSON" }}\n" +
                "          packageName: com.app.learning\n" +
                "          releaseFiles: app/build/outputs/bundle/release/app-release.aab\n" +
                "          track: production\n" +
                "          status: draft")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSplashPreview() {
    LearningTheme {
        GithubActionsContent()
    }
}