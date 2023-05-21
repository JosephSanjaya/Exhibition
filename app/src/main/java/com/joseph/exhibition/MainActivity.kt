package com.joseph.exhibition

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.joseph.exhibition.core.common.data.remoteconfig.FlagConfigRepo
import com.joseph.exhibition.ui.theme.ExhibitionTheme
import dagger.hilt.android.AndroidEntryPoint
import io.appwrite.services.Account
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var remoteConfigRepo: FlagConfigRepo

    @Inject
    lateinit var auth: Account

    @Inject
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            auth.createEmailSession(
                email = "joseph.sanjaya@yahoo.com",
                password = "Qwerty12"
            ).also {
                sharedPref.edit {
                    putString("deviceName", it.deviceName)
                }
                kotlin.runCatching {
                    val isForceUpdate = remoteConfigRepo.isForceUpdate().toString()
                    println(isForceUpdate)
                }.getOrElse {
                    println(it)
                }
                Toast.makeText(this@MainActivity, sharedPref.getString("deviceName", "null"), Toast.LENGTH_SHORT).show()
            }
        }
        setContent {
            ExhibitionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExhibitionTheme {
        Greeting("Android")
    }
}
