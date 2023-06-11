package com.joseph.exhibition.core.common.utils

import io.appwrite.models.Country
import io.appwrite.models.Session

val emptySession
    get() = Session.from(
        mapOf<String, Any>(
            "\$id" to "",
            "\$createdAt" to "",
            "userId" to "",
            "expire" to "",
            "provider" to "",
            "providerUid" to "",
            "providerAccessToken" to "",
            "providerAccessTokenExpiry" to "",
            "providerRefreshToken" to "",
            "ip" to "",
            "osCode" to "",
            "osName" to "",
            "osVersion" to "",
            "clientType" to "",
            "clientCode" to "",
            "clientName" to "",
            "clientVersion" to "",
            "clientEngine" to "",
            "clientEngineVersion" to "",
            "deviceName" to "",
            "deviceBrand" to "",
            "deviceModel" to "",
            "countryCode" to "",
            "countryName" to "",
            "current" to false,
        )
    )

val emptyCountry
    get() = Country.from(
        mapOf<String, Any>(
            "name" to "",
            "code" to ""
        )
    )