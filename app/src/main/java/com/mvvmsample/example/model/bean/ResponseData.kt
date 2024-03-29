package com.mvvmsample.example.model.bean

import java.io.Serializable

/**
 * Data class
 */
data class WanResponse<out T>(val errorCode : Int, val errorMsg : String, val data : T)

data class ArticleList(val offset: Int, val size: Int, val total: Int, val pageCount: Int,
                       val curPage: Int, val over: Boolean, val datas: List<Article>): Serializable

data class Article( val id: Int,
                    val originId: Int,
                    val title: String,
                    val chapterId: Int,
                    val chapterName: String,
                    val envelopePic: String,
                    val link: String,
                    val author: String,
                    val origin: String,
                    val publishTime: Long,
                    val zan: Int,
                    val desc: String,
                    val visible: Int,
                    val niceDate: String,
                    val niceShareDate: String,
                    val courseId: Int,
                    var collect: Boolean,
                    val apkLink:String,
                    val projectLink:String,
                    val superChapterId:Int,
                    val superChapterName:String?,
                    val type:Int,
                    val fresh:Boolean,
                    val audit:Int,
                    val prefix:String,
                    val selfVisible:Int,
                    val shareDate:Long,
                    val shareUser:String,
                    val tags:Any, // Not sure
                    val userId:Int
):Serializable

data class Banner(val desc: String,
                  val id: Int,
                  val imagePath: String,
                  val isVisible: Int,
                  val order: Int,
                  val title: String,
                  val type: Int,
                  val url: String) : Serializable

data class User(val collectIds: List<Int>,
                val email: String,
                val icon: String,
                val id: Int,
                val password: String,
                val type: Int,
                val username: String)