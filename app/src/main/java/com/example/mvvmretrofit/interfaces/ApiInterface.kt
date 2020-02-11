package com.example.mvvmretrofit.interfaces

import com.example.mvvmretrofit.utilities.BASE_URLS
import com.example.mvvmretrofit.models.UsersDC
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiInterface {

    // Api for getting the user data
    @GET("users")
    fun validateAPI(@Query("delay") delay: String): Call<UsersDC>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URLS)
                .client(customOkHttpClient())
                .build()

            return retrofit.create(ApiInterface::class.java)

        }

        private fun customOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }
    }
}