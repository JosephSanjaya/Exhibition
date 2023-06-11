package com.joseph.exhibition.features.auth.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainer
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.state
import com.joseph.exhibition.core.common.utils.emptySession
import com.joseph.exhibition.features.auth.data.SignInFormError
import com.joseph.exhibition.features.auth.domain.login.LoginPayload
import com.joseph.exhibition.features.auth.domain.login.LoginUseCase
import com.joseph.exhibition.features.auth.domain.register.RegisterFormError
import com.joseph.exhibition.features.auth.domain.register.RegisterPayload
import com.joseph.exhibition.features.auth.domain.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A [ViewModel] that handles the test logic and state management.
 *
 * @param stateFactory The factory that creates the [StateContainer] for this [ViewModel].
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    stateFactory: StateContainerFactory,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    companion object {
        private const val REGISTER_JOB_KEY = "register"
        private const val REGISTER_BODY_KEY = "register-body"
        private const val PASSWORD_SHOWN_KEY = "is-password-shown"
        private const val CONFIRM_PASSWORD_SHOWN_KEY = "is-confirm-password-shown"
        private const val REGISTER_FORM_ERROR_KEY = "form-error"
        private const val LOGIN_JOB_KEY = "login"
        private const val SIGN_IN_FORM_ERROR_KEY = "sign-in-form-error"
        private const val LOGIN_BODY_KEY = "login-body"
        private const val LOGIN_SHEET_KEY = "is-show-login-sheet"
        private const val LOGIN_PASSWORD_SHOWN_KEY = "is-login-password-shown"
    }

    // The container that holds the states for this [ViewModel].
    private val states: StateContainer = stateFactory.create(viewModelScope)

    // The state that represents the test result.
    val registerState by states.state(REGISTER_JOB_KEY, State(emptySession))
    val isShowLoginSheet by states.state(LOGIN_SHEET_KEY, State(false))
    val registerBody by states.state(REGISTER_BODY_KEY, State(RegisterPayload()))
    val isShowPassword by states.state(PASSWORD_SHOWN_KEY, State(false))
    val isConfirmShowPassword by states.state(CONFIRM_PASSWORD_SHOWN_KEY, State(false))
    val registerFormError by states.state(REGISTER_FORM_ERROR_KEY, State(RegisterFormError()))

    val loginState by states.state(LOGIN_JOB_KEY, State(emptySession))
    val signInBody by states.state(LOGIN_BODY_KEY, State(LoginPayload()))
    val isLoginShowPassword by states.state(LOGIN_PASSWORD_SHOWN_KEY, State(false))
    val signInFormError by states.state(SIGN_IN_FORM_ERROR_KEY, State(SignInFormError()))

    init {
        viewModelScope.launch {
            registerBody.collect {
                val data = it.data ?: return@collect
                states.processState(REGISTER_FORM_ERROR_KEY, State(RegisterFormError())) {
                    data.checkFormSignUp()
                }
            }
        }
        viewModelScope.launch {
            signInBody.collect {
                val data = it.data ?: return@collect
                states.processState(SIGN_IN_FORM_ERROR_KEY, State(RegisterFormError())) {
                    data.checkFormSignIn()
                }
            }
        }
    }

    // The job that performs the test logic.
    private var _registerJob: Job? = null
    val registerJob get() = _registerJob

    /**
     * Executes the test logic based on the given [intent].
     *
     * @param intent The [TestIntent] that specifies which test to perform.
     */
    fun processRegisterIntent(intent: RegisterIntent) {
        if (intent is RegisterIntent.DoRegister) {
            val data = registerBody.value.data ?: return
            _registerJob = states.processState(REGISTER_JOB_KEY, emptySession) {
                registerUseCase(data)
            }
            return
        }
        if (intent is RegisterIntent.DoLogin) {
            val data = signInBody.value.data ?: return
            _registerJob = states.processState(LOGIN_JOB_KEY, emptySession) {
                loginUseCase(data.email, data.password)
            }
            return
        }
        val (key, default, result) = when (intent) {
            is RegisterIntent.UpdateRegisterPayload -> Triple(
                REGISTER_BODY_KEY,
                State(RegisterPayload()),
                intent.payload
            )

            is RegisterIntent.PasswordVisibilityChange -> Triple(
                PASSWORD_SHOWN_KEY,
                State(false),
                isShowPassword.value.data == false
            )

            is RegisterIntent.ConfirmPasswordVisibilityChange -> Triple(
                CONFIRM_PASSWORD_SHOWN_KEY,
                State(false),
                isConfirmShowPassword.value.data == false
            )

            is RegisterIntent.LoginSheetVisibilityChange -> Triple(
                LOGIN_SHEET_KEY,
                State(false),
                intent.isShown
            )
            is RegisterIntent.UpdateSignInPayload -> Triple(
                LOGIN_BODY_KEY,
                State(LoginPayload()),
                intent.payload
            )

            is RegisterIntent.SignInPasswordVisibilityChange -> Triple(
                LOGIN_PASSWORD_SHOWN_KEY,
                State(false),
                isLoginShowPassword.value.data == false
            )
            else -> return
        }
        _registerJob = states.processState(key, default) {
            result
        }
    }
}
