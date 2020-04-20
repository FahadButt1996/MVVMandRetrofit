package com.example.mvvmretrofit.interfaces

import com.example.mvvmretrofit.models.UsersDC
import com.example.mvvmretrofit.utilities.BASE_URLS
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiInterface {

    // Api for getting the user data
    @GET("users")
    fun validateAPIAsync(@Query("delay") delay: String): Deferred<Response<UsersDC>>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URLS)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(customOkHttpClient())
                .build()

            return retrofit.create(ApiInterface::class.java)

        }

        private fun customOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
        }
    }
}