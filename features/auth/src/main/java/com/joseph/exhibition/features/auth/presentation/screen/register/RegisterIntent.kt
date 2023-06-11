package com.joseph.exhibition.features.auth.presentation.screen.register

import com.joseph.exhibition.features.auth.domain.login.LoginPayload
import com.joseph.exhibition.features.auth.domain.register.RegisterPayload

/**
 * A sealed class that represents the possible intents for the test logic.
 */
sealed class RegisterIntent {
    data class UpdateRegisterPayload(val payload: RegisterPayload) : RegisterIntent()
    object PasswordVisibilityChange : RegisterIntent()
    object ConfirmPasswordVisibilityChange : RegisterIntent()
    data class LoginSheetVisibilityChange(val isShown: Boolean) : RegisterIntent()
    data class UpdateSignInPayload(val payload: LoginPayload) : RegisterIntent()
    object SignInPasswordVisibilityChange : RegisterIntent()
    object DoRegister: RegisterIntent()
    object DoLogin: RegisterIntent()
}
