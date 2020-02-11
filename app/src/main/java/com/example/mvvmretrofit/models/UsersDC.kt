package com.example.mvvmretrofit.models

import com.google.gson.annotations.SerializedName

data class UsersDC(
    @SerializedName("data")
    val data: ArrayList<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int
) {
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String
    )
}