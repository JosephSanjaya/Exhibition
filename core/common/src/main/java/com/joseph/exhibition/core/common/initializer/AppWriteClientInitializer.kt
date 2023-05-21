package com.joseph.exhibition.core.common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.joseph.exhibition.core.common.BuildConfig
import io.appwrite.Client

/**
 * A class that initializes the AppWrite client with the given endpoint and project ID.
 * This class implements the [Initializer] interface to provide a lazy initialization of the client.
 */
class AppWriteClientInitializer : Initializer<Client> {
    // Create a new instance of the AppWrite client with the context and the configuration values from BuildConfig
    override fun create(context: Context): Client {
        return Client(context).setEndpoint(BuildConfig.APPWRITE_HOST) // Set the base URL of the AppWrite server
            .setProject(BuildConfig.APPWRITE_PROJECT_ID) // Set the project ID to use for the requests
    }

    // Return an empty list of dependencies, as this initializer does not depend on any other initializer
    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
