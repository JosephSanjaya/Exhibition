package com.joseph.exhibition.features.test.presentation.screen.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.joseph.exhibition.features.test.presentation.screen.test.viewmodel.TestIntent
import com.joseph.exhibition.features.test.presentation.screen.test.viewmodel.TestViewModel

@Composable
fun TestScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = hiltViewModel(),
    onNavigateClick: () -> Unit = {}
) {
    val name by viewModel.testState.collectAsState(State(""))
    TestScreen(name.data.orEmpty(), modifier, {
        viewModel.doTest(TestIntent.DoTest1)
    }, {
        viewModel.doTest(TestIntent.DoTest2)
    }, onNavigateClick)
}

@Composable
fun TestScreen(
    name: String,
    modifier: Modifier = Modifier,
    onButtonClick1: () -> Unit = {},
    onButtonClick2: () -> Unit = {},
    onButtonClick3: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center
        )

        Button(onClick = onButtonClick1) {
            Text(text = "Test 1")
        }

        Button(onClick = onButtonClick2) {
            Text(text = "Test 2")
        }

        Button(onClick = onButtonClick3) {
            Text(text = "Test navigate")
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun TestScreenPreview() {
    val name by remember {
        mutableStateOf("Test")
    }
    MainTheme {
        Screen {
            TestScreen(name)
        }
    }
}
