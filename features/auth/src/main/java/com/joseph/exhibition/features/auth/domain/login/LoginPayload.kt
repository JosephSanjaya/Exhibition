package com.joseph.exhibition.features.auth.domain.login

import com.google.gson.annotations.SerializedName
import com.joseph.exhibition.features.auth.data.SignInFormError

data class LoginPayload(

    @SerializedName("email")
    val email: String = "",

    @SerializedName("password")
    val password: String = "",
) {
    fun checkFormSignIn(): SignInFormError {
        var errorForm = SignInFormError(isInit = false)
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$"

        if (email.isEmpty()) {
            errorForm = errorForm.copy(emailError = "Email need to be filled.")
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorForm = errorForm.copy(emailError = "Email is not valid.")
        }

        if (password.isEmpty()) {
            errorForm = errorForm.copy(passwordError = "Password need to be filled.")
        }

        if (!passwordRegex.toRegex().matches(password)) {
            errorForm =
                errorForm.copy(passwordError = "Password need to contains number, char, and minimum 8 characters.")
        }

        return errorForm
    }
}
