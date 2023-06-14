package com.lukitateam.lukita.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.lukitateam.lukita.data.datastore.SessionDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pref: SessionDatastore,
) : ViewModel() {

    private val _profileState = Channel<ProfileState>()
    val profileState = _profileState.receiveAsFlow()

    suspend fun logout() {
        pref.removeSession()
    }
}