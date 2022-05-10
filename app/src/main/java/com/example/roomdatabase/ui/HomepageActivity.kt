package com.example.roomdatabase.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.UserListModel
import com.example.roomdatabase.adapter.userlistAdapter
import com.example.roomdatabase.databinding.ActivityHomepageBinding
import com.example.roomdatabase.viewModel.UserViewModel

class HomepageActivity : AppCompatActivity()
{
    lateinit var mUserViewModel: UserViewModel
    lateinit var binding: ActivityHomepageBinding
    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        activity = this
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.commoncolor)


        binding.rvUsersList.addOnScrollListener(object : RecyclerView.OnScrollListener()
                                                {
                                                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
                                                    {
                                                        super.onScrolled(recyclerView, dx, dy)
                                                        if (dy > 0) binding.fabAddUser.hide()
                                                        else if (dy < 0) binding.fabAddUser.show()
                                                    }
                                                })


        /*var data = ArrayList<UserListModel>()
        for (i in 0..20)
        {

            data.add(UserListModel(1, "Tejas Tripathi", "Intern", "21"))
        }
        binding.rvUsersList.adapter = userlistAdapter(data)*/


        val adapter=userlistAdapter()
        binding.rvUsersList.adapter=adapter

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })

        binding.fabAddUser.setOnClickListener {

            val intent = Intent(activity, addUserActivity::class.java)
            startActivity(intent)
        }
    }
}