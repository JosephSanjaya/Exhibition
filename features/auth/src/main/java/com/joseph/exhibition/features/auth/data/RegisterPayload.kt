package com.joseph.exhibition.features.auth.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
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
    val username: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("dob")
    val dob: LocalDate,

    @SerializedName("occupation")
    val occupation: String,

    @SerializedName("country")
    val country: Country,

    @SerializedName("company")
    val company: String,

    @SerializedName("city")
    val city: String,

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
}
