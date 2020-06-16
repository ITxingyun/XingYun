package com.xingyun.android.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xingyun.android.viewmodel.community.CommunityViewModel
import com.xingyun.android.viewmodel.community.QuestionViewModel
import com.xingyun.android.core.source.DataRepository
import com.xingyun.android.viewmodel.community.SquareViewModel
import com.xingyun.android.viewmodel.home.HomeViewModel
import com.xingyun.android.viewmodel.splash.LoginViewModel
import com.xingyun.android.viewmodel.splash.RegistryViewModel
import com.xingyun.android.viewmodel.user.UserProfileViewModel
import com.xingyun.android.viewmodel.webview.WebViewViewModel
import javax.inject.Inject

class XYViewModelFactory @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            //splash
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(dataRepository)
            modelClass.isAssignableFrom(RegistryViewModel::class.java) ->
                RegistryViewModel(dataRepository)

            //home
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(dataRepository)

            //Community
            modelClass.isAssignableFrom(CommunityViewModel::class.java) ->
                CommunityViewModel(dataRepository)
            modelClass.isAssignableFrom(SquareViewModel::class.java) ->
                SquareViewModel(dataRepository)
            modelClass.isAssignableFrom(QuestionViewModel::class.java) ->
                QuestionViewModel(dataRepository)

            //user
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) ->
                UserProfileViewModel(dataRepository)

            //WebView
            modelClass.isAssignableFrom(WebViewViewModel::class.java) ->
                WebViewViewModel()

            else ->
                throw ClassCastException("XinYunViewModelFactory can't create${modelClass.simpleName}")
        } as T
    }

}