package com.lukitateam.lukita.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.lukitateam.lukita.util.extractNameFromEmail
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SessionDatastore @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) {

    fun getSession() = context.datastore.data.map { preferences ->
        preferences[SESSION_KEY] ?: ""
    }

    suspend fun generateSession() {
        val user = firebaseAuth.currentUser
        context.datastore.edit { preferences ->
            preferences[SESSION_KEY] = user?.uid ?: ""
        }
    }

    suspend fun removeSession() {
        context.datastore.edit { preferences ->
            preferences[SESSION_KEY] = ""
        }
    }

    suspend fun generateUsername() {
        val user = firebaseAuth.currentUser

        var username = user?.email
        username = username?.let { extractNameFromEmail(it) }

        context.datastore.edit { preferences ->
            preferences[USERNAME_KEY] = username ?: ""
        }
    }

    fun getUsername() = context.datastore.data.map { preferences ->
        preferences[USERNAME_KEY] ?: ""
    }

    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("user")
        val SESSION_KEY = stringPreferencesKey("Session")
        val USERNAME_KEY = stringPreferencesKey("Username")
    }

}
