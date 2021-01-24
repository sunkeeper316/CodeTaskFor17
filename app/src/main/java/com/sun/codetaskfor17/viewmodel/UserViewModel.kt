package com.sun.codetaskfor17.viewmodel

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sun.codetaskfor17.model.UserListData
import com.sun.codetaskfor17.model.repository.UserListRepository

class UserViewModel(var userListRepository: UserListRepository)  : ViewModel(){
    var isLoad = false
    fun userListModel(query: String, page : Int, observer:Observer<UserListData>)  {
        if (!isLoad) {
            isLoad = true
            userListRepository.getUserList(query , page , 20 , callback = { throwable, response ->
                isLoad = false
                throwable?.let{
                    Log.e("userListModel","items size ${it.printStackTrace()}")
                    return@getUserList
                }
                response?.let{

                    observer.onChanged(it.body())
                }
            })
        }
    }
}