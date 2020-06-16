package com.xingyun.android.core.model

import androidx.room.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity
data class Article(
        @PrimaryKey val id: Int,
        val apkLink: String,
        val audit: Int,
        val author: String,
        val canEdit: Boolean,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val selfVisible: Int,
        val shareDate: Long,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>?,
        @Embedded val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
)

data class Tag(val name: String, val url: String)

class TagsConverters {

    @TypeConverter
    fun tagsToString(tags: List<Tag>?): String? {
        return tags?.run {
            val moShi = Moshi.Builder().build()
            val type = Types.newParameterizedType(MutableList::class.java, Tag::class.java)
            val jsonAdapter = moShi.adapter<List<Tag>>(type)
            jsonAdapter.toJson(this)
        }
    }

    @TypeConverter
    fun stringToTags(value: String?): List<Tag>? {
        val moShi = Moshi.Builder().build()
        val type = Types.newParameterizedType(MutableList::class.java, Tag::class.java)
        val jsonAdapter = moShi.adapter<List<Tag>>(type)
        return value?.run { jsonAdapter.fromJson(this) }
    }

}