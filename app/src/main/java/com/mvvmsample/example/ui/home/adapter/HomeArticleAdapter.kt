package com.mvvmsample.example.ui.home.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mvvmsample.example.R
import com.mvvmsample.example.model.bean.Article


class HomeArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article){
    private var showStar = true

    fun showStar(showStar : Boolean) {
        this.showStar = showStar
    }

    @SuppressLint("NewApi")
    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.articleTitle, Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY))
            .setText(R.id.articleAuthor, item.author)
            .setText(R.id.articleTag, "${item.superChapterName ?: ""}${item.chapterName}")
            .setText(R.id.articleTime, item.niceDate)
            .addOnClickListener(R.id.articleStar)

        val ivStar = helper.getView<ImageView>(R.id.articleStar)
        if (showStar) {
            ivStar.visibility = View.VISIBLE
            ivStar.setImageResource(if (item.collect) R.drawable.timeline_like_pressed else R.drawable.timeline_like_normal)
        } else ivStar.visibility = View.GONE
    }
}