package com.xingyun.android.viewmodel.splash

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.R
import com.xingyun.android.core.source.DataRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val dataRepository: DataRepository) : ViewModel() {
    val userName = MutableLiveData<String>()

    val password = MutableLiveData<String>()


    private val _userNameErrorMsg = MutableLiveData<@StringRes Int>()
    val userNameErrorMsg: LiveData<Int>
        get() = _userNameErrorMsg

    private val _passwordErrorMsg = MutableLiveData<@StringRes Int>()
    val passwordErrorMsg: LiveData<Int>
        get() = _passwordErrorMsg

    fun login() {
        viewModelScope.launch {
            if (verifyFiled()) {
                val user = dataRepository.login(userName.value ?: "", password.value ?: "")
                Log.e("xxx", user.toString())
            }
        }
    }

    private fun verifyFiled(): Boolean {
        var verify = true
        if (userName.value.isNullOrEmpty()) {
            _userNameErrorMsg.value = R.string.login_user_name_error_msg
            verify = false
        }
        if (password.value.isNullOrEmpty()) {
            _passwordErrorMsg.value = R.string.login_password_error_msg
            verify = false
        }
        return verify
    }


}