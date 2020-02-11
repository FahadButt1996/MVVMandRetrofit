package com.example.mvvmretrofit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmretrofit.interfaces.ApiInterface
import com.example.mvvmretrofit.models.UsersDC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val prospectSummary: MutableLiveData<UsersDC> = MutableLiveData()

    fun callUsersApi(): MutableLiveData<UsersDC> {

        val usersData = ApiInterface.create().validateAPI("5")

        usersData.enqueue(object : Callback<UsersDC> {
            override fun onResponse(
                call: Call<UsersDC>, response: Response<UsersDC>
            ) {
                if (response.isSuccessful && response.code().equals(200)) {
                    prospectSummary.postValue(response.body())
                } else {
                    prospectSummary.postValue(null)
                }
            }

            override fun onFailure(call: Call<UsersDC>, t: Throwable) {
                prospectSummary.postValue(null)
            }
        })

        return prospectSummary
    }

}