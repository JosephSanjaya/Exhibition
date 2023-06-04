package com.joseph.exhibition.features.test.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.joseph.exhibition.core.ui.presentation.components.Screen
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.features.test.presentation.navigation.TestNavHost
import dagger.hilt.android.AndroidEntryPoint
import splitties.bundle.withExtras
import splitties.intents.ActivityIntentSpec
import splitties.intents.activitySpec

@AndroidEntryPoint
class TestActivity : ComponentActivity() {
    companion object :
        ActivityIntentSpec<TestActivity, TestActivitySpec> by activitySpec(TestActivitySpec)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processSpec()
        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            MainTheme {
                Screen {
                    TestNavHost()
                }
            }
        }
    }

    private fun processSpec() {
        withExtras(TestActivitySpec) {
            // Do Something with Spec
        }
    }
}
