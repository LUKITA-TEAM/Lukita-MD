package com.lukitateam.lukita.ui.screen.splash

import androidx.lifecycle.ViewModel
import com.lukitateam.lukita.data.datastore.SessionDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref : SessionDatastore
) : ViewModel() {

    fun getSession(): Flow<String> {
        return pref.getSession()
    }

}