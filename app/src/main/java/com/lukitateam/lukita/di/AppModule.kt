package com.lukitateam.lukita.di

import com.google.firebase.auth.FirebaseAuth
import com.lukitateam.lukita.data.AuthRepository
import com.lukitateam.lukita.data.AuthRepositoryImpl
import com.lukitateam.lukita.data.datastore.SessionDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(firebaseAuth: FirebaseAuth) : AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

}