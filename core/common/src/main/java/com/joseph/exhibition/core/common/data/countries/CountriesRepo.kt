package com.joseph.exhibition.core.common.data.countries

import io.appwrite.models.CountryList

/**
 * Repository interface for retrieving a list of countries.
 */
interface CountriesRepo {
    /**
     * Retrieves a list of all countries.
     *
     * @return the list of countries.
     * @see CountryList
     */
    suspend fun getAll(): CountryList
}
