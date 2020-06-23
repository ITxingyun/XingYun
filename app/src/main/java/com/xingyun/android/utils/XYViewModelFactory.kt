package com.xingyun.android.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xingyun.android.model.source.ArticleRepository
import com.xingyun.android.model.source.UserProfileRepository
import com.xingyun.android.ui.article.ArticlesViewModel
import com.xingyun.android.ui.login.LoginViewModel
import com.xingyun.android.ui.login.RegistryViewModel
import com.xingyun.android.ui.project.ProjectViewModel
import com.xingyun.android.ui.search.SearchViewModel
import com.xingyun.android.ui.system.SystemViewModel
import com.xingyun.android.ui.user.UserProfileViewModel
import com.xingyun.android.ui.webview.WebViewViewModel
import javax.inject.Inject

class XYViewModelFactory @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            //login
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(userProfileRepository)
            modelClass.isAssignableFrom(RegistryViewModel::class.java) ->
                RegistryViewModel(userProfileRepository)

            modelClass.isAssignableFrom(ArticlesViewModel::class.java) ->
                ArticlesViewModel(articleRepository)

            //system
            modelClass.isAssignableFrom(SystemViewModel::class.java) ->
                SystemViewModel()

            //search
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->
                SearchViewModel(articleRepository)

            //project
            modelClass.isAssignableFrom(ProjectViewModel::class.java) ->
                ProjectViewModel(articleRepository)

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