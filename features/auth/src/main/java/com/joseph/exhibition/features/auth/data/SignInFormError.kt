package com.joseph.exhibition.features.auth.data

data class SignInFormError(
    val isInit: Boolean = true,
    val emailError: String = "",
    val passwordError: String = "",
) {
    fun isValid(): Boolean {
        return !isInit && emailError.isEmpty() && passwordError.isEmpty()
    }
}
