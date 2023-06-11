package com.joseph.exhibition.features.auth.domain.register

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.joseph.exhibition.core.common.utils.emptyCountry
import io.appwrite.models.Country
import java.time.LocalDate

/**
 * Data class representing the payload for user registration.
 *
 * @property username the username of the user.
 * @property firstName the first name of the user.
 * @property lastName the last name of the user.
 * @property email the email of the user.
 * @property password the password of the user.
 * @property tagline the tagline of the user.
 * @property dob the date of birth of the user.
 * @property occupation the occupation of the user.
 * @property country the country of the user.
 * @property company the company of the user.
 * @property city the city of the user.
 * @property socialMedia the social media profiles of the user.
 */
@Keep
data class RegisterPayload(
    @SerializedName("username")
    val username: String = "",

    @SerializedName("firstName")
    val firstName: String = "",

    @SerializedName("lastName")
    val lastName: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("password")
    val password: String = "",

    @SerializedName("confirmPassword")
    val confirmPassword: String = "",

    @SerializedName("tagline")
    val tagline: String = "",

    @SerializedName("dob")
    val dob: LocalDate = LocalDate.MIN,

    @SerializedName("occupation")
    val occupation: String = "",

    @SerializedName("country")
    val country: Country = emptyCountry,

    @SerializedName("company")
    val company: String = "",

    @SerializedName("city")
    val city: String = "",

    @SerializedName("socialMedia")
    val socialMedia: List<String> = listOf()
) {
    /**
     * Returns the payload map containing non-excluded fields.
     *
     * @return the payload map.
     */
    fun getPayloadMap(): Map<String, Any> {
        val excludedFields = setOf("username", "email", "password", "tagline")

        val payloadMap = mutableMapOf<String, Any>()

        if (!excludedFields.contains("firstName")) {
            payloadMap["firstName"] = this.firstName
        }
        if (!excludedFields.contains("lastName")) {
            payloadMap["lastName"] = this.lastName
        }
        if (!excludedFields.contains("dob")) {
            payloadMap["dob"] = this.dob
        }
        if (!excludedFields.contains("occupation")) {
            payloadMap["occupation"] = this.occupation
        }
        if (!excludedFields.contains("country")) {
            payloadMap["country"] = this.country.toMap()
        }
        if (!excludedFields.contains("company")) {
            payloadMap["company"] = this.company
        }
        if (!excludedFields.contains("city")) {
            payloadMap["city"] = this.city
        }
        if (!excludedFields.contains("socialMedia")) {
            payloadMap["socialMedia"] = this.socialMedia
        }

        return payloadMap
    }

    fun checkFormSignUp(): RegisterFormError {
        var errorForm = RegisterFormError(isInit = false)
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$"
        val taglineRegex = "^#\\w{7,}$"
        //TODO: replace with resources
        if (username.isEmpty()) {
            errorForm = errorForm.copy(userNameError = "Username need to be filled.")
        }

        if (tagline.isEmpty()) {
            errorForm = errorForm.copy(taglineError = "Tagline need to be filled.")
        }

        if (!taglineRegex.toRegex().matches(tagline)) {
            errorForm =
                errorForm.copy(taglineError = "Tagline format is not valid and minimum 8 char.")
        }

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

        if (!password.contentEquals(confirmPassword)) {
            errorForm = errorForm.copy(
                passwordError = "Password not matched.",
                confirmPasswordError = "Password not matched.",
            )
        }
        return errorForm
    }
}
