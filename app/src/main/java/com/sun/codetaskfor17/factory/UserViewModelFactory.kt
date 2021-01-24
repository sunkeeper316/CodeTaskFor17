package com.sun.codetaskfor17.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sun.codetaskfor17.model.repository.UserListRepository
import com.sun.codetaskfor17.viewmodel.UserViewModel

class UserViewModelFactory(private var userListRepository: UserListRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}