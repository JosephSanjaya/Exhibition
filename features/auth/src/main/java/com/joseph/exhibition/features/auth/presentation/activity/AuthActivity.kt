package com.joseph.exhibition.features.auth.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.joseph.exhibition.core.ui.presentation.components.Screen
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.features.auth.presentation.navigation.AuthNavHost
import dagger.hilt.android.AndroidEntryPoint
import splitties.bundle.withExtras
import splitties.intents.ActivityIntentSpec
import splitties.intents.activitySpec

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    companion object :
        ActivityIntentSpec<AuthActivity, AuthActivitySpec> by activitySpec(AuthActivitySpec)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processSpec()
        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            MainTheme {
                Screen {
                    AuthNavHost() {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        }
    }

    private fun processSpec() {
        withExtras(AuthActivitySpec) {
            // Do Something with Spec
        }
    }
}