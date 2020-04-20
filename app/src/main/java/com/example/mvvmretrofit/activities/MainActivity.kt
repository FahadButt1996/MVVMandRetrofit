package com.example.mvvmretrofit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.adapters.UsersAdapter
import com.example.mvvmretrofit.interfaces.GenericAdapterCallback
import com.example.mvvmretrofit.models.UsersDC
import com.example.mvvmretrofit.repository.UserRepository
import com.example.mvvmretrofit.utilities.hideProgressDialog
import com.example.mvvmretrofit.utilities.showProgressDialog
import com.example.mvvmretrofit.utilities.showToast
import com.example.mvvmretrofit.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), GenericAdapterCallback, CoroutineScope {

    private val compositeJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + compositeJob

    lateinit var mainViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        getUsers()
    }

    private fun initData() {
        mainViewModel = ViewModelProviders.of(
            this,
            UserViewModel.UserViewModelFactory(
                UserRepository()
            )
        ).get(UserViewModel::class.java)
    }

    private fun getUsers() {
        showProgressDialog(this)
        launch {
            val userData = mainViewModel.getUsers()
            hideProgressDialog()
            if (userData != null && !userData.data.isNullOrEmpty()) {
                setUserRecyclerView(userData.data)
            }
        }

    }

    fun setUserRecyclerView(userData: List<UsersDC.Data>) {
        val adapter = UsersAdapter(userData, this@MainActivity, this@MainActivity)
        user_recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    override fun <T> getClickedObject(clickedObj: T, position: T, callingID: String) {
        when (callingID) {
            "User" -> {
                val data = clickedObj as UsersDC.Data
                showToast("${data.first_name} ${data.last_name}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
