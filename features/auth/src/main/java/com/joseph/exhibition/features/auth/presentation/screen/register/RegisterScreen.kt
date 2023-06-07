package com.joseph.exhibition.features.auth.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.ui.presentation.components.Screen
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme

@Composable
fun RegisterScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val name by viewModel.registerState.collectAsState(State(""))
    RegisterScreen(name.data.toString(), modifier)
}

@Composable
fun RegisterScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun RegisterScreenPreview() {
    val name by remember {
        mutableStateOf("Test")
    }
    MainTheme {
        Screen {
            RegisterScreen(name)
        }
    }
}
