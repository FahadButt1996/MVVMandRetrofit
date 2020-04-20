package com.example.mvvmretrofit.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.models.UsersDC
import com.example.mvvmretrofit.repository.UserRepository

class UserViewModel(val userRepository: UserRepository) : ViewModel() {

    suspend fun getUsers(): UsersDC? {
        return userRepository.getUsersFromNetwork()
    }

    class UserViewModelFactory(
        private val repository: UserRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(repository) as T
        }
    }

}