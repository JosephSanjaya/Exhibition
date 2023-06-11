package com.joseph.exhibition.presentation.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.joseph.exhibition.core.ui.presentation.components.Screen
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.features.auth.presentation.activity.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        setComposeContent()
    }

    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashView,
                View.TRANSLATION_Y.name,
                0f,
                -splashView.view.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 500L
            slideUp.doOnEnd { splashView.remove() }
            slideUp.start()
        }
    }

    private fun setComposeContent() {
        setContent {
            MainTheme {
                Screen {
                    SplashScreen(onDataFetchedSuccess = {
                        onDataFetchedSuccess()
                    })
                }
            }
        }
    }

    private fun onDataFetchedSuccess() {
        val intent = Intent(this@SplashActivity, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
