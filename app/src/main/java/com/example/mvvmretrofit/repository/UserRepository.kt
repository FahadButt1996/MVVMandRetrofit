package com.example.mvvmretrofit.repository

import com.example.mvvmretrofit.interfaces.ApiInterface
import com.example.mvvmretrofit.models.UsersDC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    suspend fun getUsersFromNetwork(): UsersDC? {
        val userData = withContext(Dispatchers.IO) {
            ApiInterface.create().validateAPIAsync("5").await()
        }
        if (userData.isSuccessful && userData.code() == 200) {
            return userData.body()!!
        } else {
            return null
        }
    }
}