package com.joseph.exhibition.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.joseph.exhibition.R
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.ui.presentation.components.Background
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.domain.splash.BlockingType

/**
 * Composable function for displaying the splash screen.
 *
 * @param modifier The modifier for styling the composable.
 * @param onDataFetchedSuccess Callback invoked when data fetching is successful.
 * @param viewModel The SplashViewModel instance to handle the business logic.
 */
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onDataFetchedSuccess: () -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {
    val splashState by viewModel.splashState.collectAsState(
        State<Pair<BlockingType?, String?>>(null to null)
    )
    SplashScreenInternal(
        modifier,
        onLoadInvoked = {
            viewModel.doLoading()
        },
        onDataFetchedSuccess = onDataFetchedSuccess,
        splashState
    )
}

/**
 * Internal implementation of the splash screen.
 *
 * @param modifier The modifier for styling the composable.
 * @param onLoadInvoked Callback invoked when loading is triggered.
 * @param onDataFetchedSuccess Callback invoked when data fetching is successful.
 * @param splashState The state representing the splash screen state.
 */
@Composable
private fun SplashScreenInternal(
    modifier: Modifier = Modifier,
    onLoadInvoked: () -> Unit = {},
    onDataFetchedSuccess: () -> Unit = {},
    splashState: State<Pair<BlockingType?, String?>> = State(null to null),
) {
    if (splashState.isFinished) {
        val (blocking, message) = splashState.data ?: (null to null)
        when (blocking) {
            null -> onDataFetchedSuccess()
            BlockingType.MAINTENANCE -> ToastUtils.showLong(message)
            else -> ToastUtils.showLong(message)
        }
    }
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (versions, center) = createRefs()
        CenterLogo(
            Modifier.constrainAs(center) {
                centerTo(parent)
            }
        )
        BottomVersion(
            modifier = Modifier
                .constrainAs(versions) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                }
        )
    }
    LaunchedEffect(Unit) {
        onLoadInvoked()
    }
}

/**
 * Composable function for displaying the center logo.
 *
 * @param modifier The modifier for styling the composable.
 */
@Composable
private fun CenterLogo(modifier: Modifier = Modifier) {
    Column(
        modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (LocalInspectionMode.current) {
            // Display placeholder image in inspection mode
            Image(
                painter = painterResource(id = R.drawable.ic_big_logo),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        } else {
            // Display async image loading in normal mode
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.ic_big_logo)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
        Text(text = stringResource(id = R.string.app_name))
    }
}

/**
 * Composable function for displaying the bottom version text.
 *
 * @param modifier The modifier for styling the composable.
 */
@Composable
private fun BottomVersion(modifier: Modifier = Modifier) {
    val version = if (LocalInspectionMode.current) "1.0.0" else AppUtils.getAppVersionName()
    Text(text = version, modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}

/**
 * Preview function for displaying the splash screen.
 */
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    MainTheme {
        Background() {
            SplashScreenInternal()
        }
    }
}
