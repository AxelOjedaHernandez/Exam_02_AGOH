package com.upiiz.exam_02_agoh.service

import com.upiiz.exam_02_agoh.models.Comment
import com.upiiz.exam_02_agoh.models.Post
import com.upiiz.exam_02_agoh.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts")
    fun getPosts(@Query("userId") userId: Int): Call<List<Post>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Call<List<Comment>>
}

