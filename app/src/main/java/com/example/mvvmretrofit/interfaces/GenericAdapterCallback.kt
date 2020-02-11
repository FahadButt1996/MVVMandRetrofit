package com.example.mvvmretrofit.interfaces

interface GenericAdapterCallback {
    fun <T> getClickedObject(clickedObj: T, position: T, callingID: String = "")
}