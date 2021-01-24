package com.sun.codetaskfor17.model.repository

import android.util.Log
import com.sun.codetaskfor17.api.AppClientManager
import com.sun.codetaskfor17.api.GitHubService
import com.sun.codetaskfor17.model.UserListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListRepository {

    private val client =  AppClientManager.client
    fun getUserList(query: String, page: Int, pageSize: Int ,callback:(Throwable?, Response<UserListData>?) -> Unit ) {
        val gitHubService = client.create(GitHubService::class.java)
        gitHubService.getUserList(query, page, pageSize).enqueue(object : Callback<UserListData> {

            override fun onResponse(call: Call<UserListData>, response: Response<UserListData>) {
                callback(null,response)


            }

            override fun onFailure(call: Call<UserListData>, t: Throwable) {
                callback(t,null)

            }
        })

    }
}