package com.joseph.exhibition.features.auth.presentation.screen.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.utils.emptySession
import com.joseph.exhibition.core.ui.presentation.components.InputBox
import com.joseph.exhibition.core.ui.presentation.components.InputBoxInverse
import com.joseph.exhibition.core.ui.presentation.components.LoadingDialog
import com.joseph.exhibition.core.ui.presentation.components.Screen
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.core.ui.presentation.themes.appColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.AppColor
import com.joseph.exhibition.features.auth.R
import com.joseph.exhibition.features.auth.data.SignInFormError
import com.joseph.exhibition.features.auth.domain.login.LoginPayload
import com.joseph.exhibition.features.auth.domain.register.RegisterFormError
import com.joseph.exhibition.features.auth.domain.register.RegisterPayload
import io.appwrite.models.Session
import kotlinx.coroutines.launch

@Composable
internal fun RegisterScreenRoute(
    modifier: Modifier = Modifier, viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerPayload by viewModel.registerBody.collectAsState(State(RegisterPayload()))
    val registerFormError by viewModel.registerFormError.collectAsState(State(RegisterFormError()))
    val isPasswordShown by viewModel.isShowPassword.collectAsState(State(false))
    val isConfirmPasswordShown by viewModel.isConfirmShowPassword.collectAsState(State(false))
    val isLoginShown by viewModel.isShowLoginSheet.collectAsState(State(false))
    val isLoginPasswordShown by viewModel.isLoginShowPassword.collectAsState(State(false))
    val loginFormError by viewModel.signInFormError.collectAsState(State(SignInFormError()))
    val signInPayload by viewModel.signInBody.collectAsState(State(LoginPayload()))
    val registerState by viewModel.registerState.collectAsState(State(emptySession))
    val loginState by viewModel.loginState.collectAsState(State(emptySession))
    RegisterScreen(
        modifier,
        registerPayload = registerPayload.data ?: RegisterPayload(),
        registerFormError = registerFormError.data ?: RegisterFormError(),
        registerState = registerState,
        signInState = loginState,
        isPasswordShown = isPasswordShown.data == true,
        isConfirmPasswordShown = isConfirmPasswordShown.data == true,
        signInPayload = signInPayload.data ?: LoginPayload(),
        isLoginShown = isLoginShown.data == true,
        isLoginPasswordShown = isLoginPasswordShown.data == true,
        loginFormError = loginFormError.data ?: SignInFormError(),
        sendIntent = {
            viewModel.processRegisterIntent(it)
        })
}

@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    signInState: State<Session> = State(),
    registerState: State<Session> = State(),
    registerPayload: RegisterPayload = RegisterPayload(),
    signInPayload: LoginPayload = LoginPayload(),
    registerFormError: RegisterFormError = RegisterFormError(),
    isPasswordShown: Boolean = false,
    isConfirmPasswordShown: Boolean = false,
    isLoginShown: Boolean = false,
    isLoginPasswordShown: Boolean = false,
    loginFormError: SignInFormError = SignInFormError(),
    sendIntent: (RegisterIntent) -> Unit = {}
) {
    val appColor = appColor()
    LoadingDialog(isLoadingShow = registerState.isLoading, onDismissRequest = {})
    LoadingDialog(isLoadingShow = signInState.isLoading, onDismissRequest = {})
    ConstraintLayout(
        modifier.fillMaxSize()
    ) {
        val (headerRef, bodyRef, signUpRef, signInRef) = createRefs()

        Header(appColor, modifier = Modifier.constrainAs(headerRef) {
            top.linkTo(parent.top)
        })

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .constrainAs(bodyRef) {
                    top.linkTo(headerRef.bottom)
                    bottom.linkTo(signUpRef.top)
                    centerHorizontallyTo(parent)
                    height = Dimension.fillToConstraints
                }
                .padding(top = 8.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            NameSection(
                registerPayload,
                modifier = Modifier.fillMaxWidth(),
                registerFormError,
                sendIntent
            )

            Text(
                modifier = Modifier.padding(bottom = 16.dp, top = 4.dp),
                text = "Remember! You cannot change the username and tagline.",
                style = MaterialTheme.typography.bodySmall,
                color = appColor.primary.darker
            )
            InputBox(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
                title = "Email",
                hint = "dublin12@example",
                errorMsg = registerFormError.takeIf { !it.isInit }?.emailError ?: "",
                input = registerPayload.email,
                onInputChange = {
                    sendIntent(RegisterIntent.UpdateRegisterPayload(registerPayload.copy(email = it)))
                },
                isRequired = true,
                endIcon = painterResource(id = R.drawable.ic_mail) to {})
            InputBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                title = "Password",
                hint = "************",
                errorMsg = registerFormError.takeIf { !it.isInit }?.passwordError ?: "",
                input = registerPayload.password,
                onInputChange = {
                    sendIntent(RegisterIntent.UpdateRegisterPayload(registerPayload.copy(password = it)))
                },
                isRequired = true,
                endIcon = painterResource(id = if (isPasswordShown) R.drawable.ic_eye_on else R.drawable.ic_eye_off) to {
                    sendIntent(RegisterIntent.PasswordVisibilityChange)
                },
                visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
            )
            InputBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                title = "Confirmation Password",
                hint = "************",
                errorMsg = registerFormError.takeIf { !it.isInit }?.confirmPasswordError ?: "",
                input = registerPayload.confirmPassword,
                onInputChange = {
                    sendIntent(
                        RegisterIntent.UpdateRegisterPayload(
                            registerPayload.copy(
                                confirmPassword = it
                            )
                        )
                    )
                },
                isRequired = true,
                endIcon = painterResource(id = if (isConfirmPasswordShown) R.drawable.ic_eye_on else R.drawable.ic_eye_off) to {
                    sendIntent(RegisterIntent.ConfirmPasswordVisibilityChange)
                },
                visualTransformation = if (isConfirmPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
            )
        }

        Button(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .constrainAs(signUpRef) {
                bottom.linkTo(signInRef.top)
            },
            enabled = registerFormError.isValid(),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(containerColor = appColor.primary.normalActive),
            onClick = {
                sendIntent(RegisterIntent.DoRegister)
            }) {
            Text(text = "Sign Up")
        }

        Card(Modifier
            .clickable {
                sendIntent(RegisterIntent.LoginSheetVisibilityChange(true))
            }
            .fillMaxWidth()
            .constrainAs(signInRef) {
                bottom.linkTo(parent.bottom)
            },
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            colors = CardDefaults.cardColors(containerColor = appColor.secondary.normalActive)
        ) {
            Row(
                Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_up),
                    contentDescription = "Show Sign in"
                )
                Text(text = "Sign In", style = MaterialTheme.typography.labelMedium)
            }
        }
        LoginSheet(
            appColor,
            isLoginShown = isLoginShown,
            isLoginPasswordShown = isLoginPasswordShown,
            loginPayload = signInPayload,
            loginFormError = loginFormError,
            sendIntent = sendIntent
        )
    }
}

@Composable
private fun Header(
    appColor: AppColor,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        val imgSrc = R.drawable.ic_big_logo
        val imageModifier = Modifier
            .padding(top = 51.dp)
            .height(100.dp)
            .width(100.dp)
        if (LocalInspectionMode.current) {
            // Display placeholder image in inspection mode
            Image(
                painter = painterResource(id = imgSrc),
                contentDescription = stringResource(R.string.app_name),
                modifier = imageModifier
            )
        } else {
            // Display async image loading in normal mode
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(imgSrc).crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Fit,
                modifier = imageModifier
            )
        }
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "One step away to become a member",
            style = MaterialTheme.typography.titleMedium,
            color = appColor.neutral.darker
        )
    }
}

@Composable
private fun NameSection(
    payload: RegisterPayload,
    modifier: Modifier = Modifier,
    formError: RegisterFormError = RegisterFormError(),
    sendIntent: (RegisterIntent) -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        InputBox(
            Modifier
                .weight(1f, true)
                .padding(end = 12.dp),
            title = "Username",
            hint = "Dublin",
            errorMsg = formError.takeIf { !it.isInit }?.userNameError ?: "",
            input = payload.username,
            onInputChange = {
                sendIntent(RegisterIntent.UpdateRegisterPayload(payload.copy(username = it)))
            },
            isRequired = true
        )
        InputBox(
            Modifier.weight(1f, true),
            title = "Tagline",
            hint = "#12JK3",
            errorMsg = formError.takeIf { !it.isInit }?.taglineError ?: "",
            input = payload.tagline,
            onInputChange = {
                sendIntent(RegisterIntent.UpdateRegisterPayload(payload.copy(tagline = it)))
            },
            isRequired = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginSheet(
    appColor: AppColor,
    modifier: Modifier = Modifier,
    loginPayload: LoginPayload = LoginPayload(),
    isLoginShown: Boolean = false,
    isLoginPasswordShown: Boolean = false,
    loginFormError: SignInFormError = SignInFormError(),
    sendIntent: (RegisterIntent) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    if (isLoginShown) {
        ModalBottomSheet(
            modifier = modifier,
            onDismissRequest = {
                sendIntent(RegisterIntent.LoginSheetVisibilityChange(false))
            },
            sheetState = sheetState,
            containerColor = appColor.secondary.normalActive
        ) {
            LoginScreen(
                appColor,
                Modifier,
                loginPayload = loginPayload,
                isLoginPasswordShown = isLoginPasswordShown,
                loginFormError = loginFormError,
                onSignUpClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            sendIntent(RegisterIntent.LoginSheetVisibilityChange(false))
                        }
                    }
                },
                sendIntent = sendIntent
            )
        }
    }
}

@Composable
private fun LoginScreen(
    appColor: AppColor,
    modifier: Modifier = Modifier,
    loginPayload: LoginPayload = LoginPayload(),
    isLoginPasswordShown: Boolean = false,
    loginFormError: SignInFormError = SignInFormError(),
    onSignUpClick: () -> Unit = {},
    sendIntent: (RegisterIntent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Sign In",
            style = MaterialTheme.typography.titleLarge,
            color = appColor.neutral.light
        )
        InputBoxInverse(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            title = "Email",
            hint = "dublin12@example",
            errorMsg = loginFormError.takeIf { !it.isInit }?.emailError ?: "",
            input = loginPayload.email,
            iconColorFilter = ColorFilter.tint(appColor.neutral.light),
            onInputChange = {
                sendIntent(RegisterIntent.UpdateSignInPayload(loginPayload.copy(email = it)))
            },
            isRequired = true,
            endIcon = painterResource(id = R.drawable.ic_mail) to {})
        InputBoxInverse(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            title = "Password",
            hint = "************",
            errorMsg = loginFormError.takeIf { !it.isInit }?.passwordError ?: "",
            input = loginPayload.password,
            iconColorFilter = ColorFilter.tint(appColor.neutral.light),
            onInputChange = {
                sendIntent(RegisterIntent.UpdateSignInPayload(loginPayload.copy(password = it)))
            },
            isRequired = true,
            endIcon = painterResource(id = if (isLoginPasswordShown) R.drawable.ic_eye_on else R.drawable.ic_eye_off) to {
                sendIntent(RegisterIntent.SignInPasswordVisibilityChange)
            },
            visualTransformation = if (isLoginPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = onSignUpClick,
            colors = ButtonDefaults.outlinedButtonColors(),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, appColor.primary.normalActive)
        ) {
            Text(text = "Sign Up")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                sendIntent(RegisterIntent.DoLogin)
            },
            enabled = loginFormError.isValid(),
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Sign In")
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun RegisterScreenPreview() {
    MainTheme {
        Screen {
            RegisterScreen()
        }
    }
}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun LoginSheetPreview() {
    MainTheme {
        Screen(modifier = Modifier.background(AppColor(true).secondary.normalActive)) {
            LoginScreen(AppColor(true))
        }
    }
}
