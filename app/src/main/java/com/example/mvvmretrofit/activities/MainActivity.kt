package com.example.mvvmretrofit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.adapters.UsersAdapter
import com.example.mvvmretrofit.interfaces.GenericAdapterCallback
import com.example.mvvmretrofit.models.UsersDC
import com.example.mvvmretrofit.utilities.*
import com.example.mvvmretrofit.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GenericAdapterCallback {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        getUserData()
    }

    private fun getUserData() {
        if(checkNetworkConnectivity(this@MainActivity)) {
            showProgressDialog(this)
            mainViewModel.callUsersApi().observe(ProcessLifecycleOwner.get(), Observer { userData ->
                if (userData != null && !userData.data.isNullOrEmpty()) {
                    setUserRecyclerView(userData.data)
                }
                hideProgressDialog()
            })
        } else {
            showToast(CHECK_INTERNET)
        }
    }

    fun setUserRecyclerView(userData: List<UsersDC.Data>) {
        val adapter = UsersAdapter(userData, this@MainActivity, this@MainActivity)
        user_recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    // for handling the clicks of Recyclerview
    override fun <T> getClickedObject(clickedObj: T, position: T, callingID: String) {
        when (callingID) {
            "User" -> {
                val data = clickedObj as UsersDC.Data
                showToast("${data.first_name} ${data.last_name}")
            }
        }
    }
}
