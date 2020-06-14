package com.xingyun.android.core.source

import com.xingyun.android.core.model.Article


interface DataSource {
    fun loadQuestionsAndAnswers(): List<Article>

    fun loadSquareArticles(): List<Article>
}
