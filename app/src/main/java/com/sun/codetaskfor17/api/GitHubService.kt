package com.sun.codetaskfor17.api

import com.sun.codetaskfor17.model.UserListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("/search/users")
    fun getUserList(@Query("q") query: String,
                    @Query("page") page: Int,
                    @Query("per_page") pageSize: Int): Call<UserListData>
}