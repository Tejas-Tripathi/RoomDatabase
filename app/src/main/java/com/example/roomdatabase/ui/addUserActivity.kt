package com.example.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ActivityAddUserBinding
import com.example.roomdatabase.model.UserListModel
import com.example.roomdatabase.viewModel.UserViewModel


class addUserActivity : AppCompatActivity()
{

    lateinit var binding: ActivityAddUserBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.commoncolor)


        binding.ivBackArrow.setOnClickListener {
            finish()
        }

        binding.tvSubmit.setOnClickListener {
            val name=binding.edtName.text.toString()
            val age=binding.edtAge.text.toString()
            val designation=binding.edtDesignation.text.toString()
            val user=UserListModel(0, name, age, designation)
            if(name.isNotEmpty() && age.isNotEmpty() && designation.isNotEmpty())
            {
                mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                mUserViewModel.addUser(user)
                Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Fill up all the fields", Toast.LENGTH_LONG).show()
            }


        }
    }
}