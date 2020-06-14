package com.xingyun.android.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xingyun.android.core.source.DataRepository

class UserProfileViewModel(dataRepository: DataRepository) : ViewModel() {

    private val _login = MutableLiveData<Unit>()
    val login: LiveData<Unit>
        get() = _login

    fun login() {
        _login.value = Unit
    }

}