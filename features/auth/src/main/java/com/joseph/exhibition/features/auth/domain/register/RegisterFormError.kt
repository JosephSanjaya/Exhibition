package com.joseph.exhibition.features.auth.domain.register

data class RegisterFormError(
    val isInit: Boolean = true,
    val userNameError: String = "",
    val taglineError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = ""
) {
    fun isValid(): Boolean {
        return !isInit && userNameError.isEmpty() && taglineError.isEmpty() && emailError.isEmpty()
                && passwordError.isEmpty() && confirmPasswordError.isEmpty()
    }
}
