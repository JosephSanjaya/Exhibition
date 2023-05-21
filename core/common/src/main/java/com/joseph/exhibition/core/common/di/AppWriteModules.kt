package com.joseph.exhibition.core.common.di

import android.content.Context
import androidx.startup.AppInitializer
import com.joseph.exhibition.core.common.initializer.AppWriteClientInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.services.Avatars
import io.appwrite.services.Databases
import io.appwrite.services.Functions
import io.appwrite.services.Locale
import io.appwrite.services.Realtime
import io.appwrite.services.Storage
import io.appwrite.services.Teams
import javax.inject.Singleton

/**
 * A Dagger module that provides common dependencies for the app.
 * This module is installed in the [SingletonComponent] scope, which means
 * the dependencies are available throughout the app and have a single instance.
 */
@InstallIn(SingletonComponent::class)
@Module
class AppWriteModules {
    /**
     * Provides an instance of the [Client] class for interacting with the AppWrite server.
     * The client is initialized using the [AppWriteClientInitializer] class and the application context.
     * @param context The application context.
     * @return The AppWrite client instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteClient(@ApplicationContext context: Context): Client {
        return AppInitializer.getInstance(context)
            .initializeComponent(AppWriteClientInitializer::class.java)
    }

    /**
     * Provides an instance of the [Account] class for managing user accounts on the AppWrite server.
     * The account is created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite account instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteAuth(client: Client): Account {
        return Account(client)
    }

    /**
     * Provides an instance of the [Storage] class for managing files on the AppWrite server.
     * The storage is created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite storage instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteStorage(client: Client): Storage {
        return Storage(client)
    }

    /**
     * Provides an instance of the [Functions] class for executing cloud functions on the AppWrite server.
     * The functions are created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite functions instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteFunctions(client: Client): Functions {
        return Functions(client)
    }

    /**
     * Provides an instance of the [Databases] class for managing collections and documents
     * on the AppWrite server.
     * The databases are created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite databases instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteDatabases(client: Client): Databases {
        return Databases(client)
    }

    /**
     * Provides an instance of the [Realtime] class for subscribing to realtime events on
     * the AppWrite server.
     * The realtime is created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite realtime instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteRealtime(client: Client): Realtime {
        return Realtime(client)
    }

    /**
     * Provides an instance of the [Avatars] class for generating avatars on the AppWrite server.
     * The avatars are created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite avatars instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteAvatars(client: Client): Avatars {
        return Avatars(client)
    }

    /**
     * Provides an instance of the [Locale] class for getting information about
     * the user's locale on the AppWrite server.
     * The locale is created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite locale instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteLocale(client: Client): Locale {
        return Locale(client)
    }

    /**
     * Provides an instance of the [Teams] class for managing teams and memberships on the AppWrite
     * server.
     * The teams are created using the [Client] instance provided by this module.
     * @param client The AppWrite client instance.
     * @return The AppWrite teams instance.
     */
    @Provides
    @Singleton
    fun provideAppWriteTeams(client: Client): Teams {
        return Teams(client)
    }
}
