package com.xingyun.android.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xingyun.android.model.source.ArticleRepository

class UserProfileViewModel(articleRepository: ArticleRepository) : ViewModel() {

    private val _login = MutableLiveData<Unit>()
    val login: LiveData<Unit>
        get() = _login

    fun login() {
        _login.value = Unit
    }

}