package com.example.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ActivityAddUserBinding
import com.example.roomdatabase.databinding.ActivityUpdateUserBinding
import com.example.roomdatabase.model.UserListModel
import com.example.roomdatabase.viewModel.UserViewModel

class UpdateUserActivity : AppCompatActivity()
{

    lateinit var data:UserListModel
    lateinit var binding:ActivityUpdateUserBinding
    var userid=0
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_update_user)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.commoncolor)

        binding.ivBackArrow.setOnClickListener {
            finish()
        }


        intent?.let {
            data = (it.extras!!.getParcelable("data") as UserListModel?)!!
            userid= data.id.toInt()
            binding.edtName.setText(data.Name.toString())
            binding.edtAge.setText(data.Age.toString())
            binding.edtDesignation.setText(data.Designation.toString())

        }

        binding.tvSubmit.setOnClickListener {
            val name=binding.edtName.text.toString()
            val age=binding.edtAge.text.toString()
            val designation=binding.edtDesignation.text.toString()
            val user= UserListModel(userid, name, age, designation)
            if(name.isNotEmpty() && age.isNotEmpty() && designation.isNotEmpty())
            {
                mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                mUserViewModel.updateUser(user)
                Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
                Toast.makeText(this, "Fill up all the fields", Toast.LENGTH_LONG).show()
            }

        }

        binding.ivDelete.setOnClickListener {
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.deleteUser(data)
            Toast.makeText(this, "Data Removed", Toast.LENGTH_LONG).show()
            finish()
        }

    }
}