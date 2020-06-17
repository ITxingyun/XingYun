package com.xingyun.android.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xingyun.android.viewmodel.community.CommunityViewModel
import com.xingyun.android.viewmodel.community.QuestionViewModel
import com.xingyun.android.core.source.ArticleRepository
import com.xingyun.android.core.source.UserProfileRepository
import com.xingyun.android.viewmodel.community.SquareViewModel
import com.xingyun.android.viewmodel.home.HomeViewModel
import com.xingyun.android.viewmodel.splash.LoginViewModel
import com.xingyun.android.viewmodel.splash.RegistryViewModel
import com.xingyun.android.viewmodel.user.UserProfileViewModel
import com.xingyun.android.viewmodel.webview.WebViewViewModel
import javax.inject.Inject

class XYViewModelFactory @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            //splash
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(userProfileRepository)
            modelClass.isAssignableFrom(RegistryViewModel::class.java) ->
                RegistryViewModel(userProfileRepository)

            //home
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(articleRepository)

            //Community
            modelClass.isAssignableFrom(CommunityViewModel::class.java) ->
                CommunityViewModel(articleRepository)
            modelClass.isAssignableFrom(SquareViewModel::class.java) ->
                SquareViewModel(articleRepository)
            modelClass.isAssignableFrom(QuestionViewModel::class.java) ->
                QuestionViewModel(articleRepository)

            //user
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) ->
                UserProfileViewModel(articleRepository)

            //WebView
            modelClass.isAssignableFrom(WebViewViewModel::class.java) ->
                WebViewViewModel()

            else ->
                throw ClassCastException("XinYunViewModelFactory can't create${modelClass.simpleName}")
        } as T
    }

}