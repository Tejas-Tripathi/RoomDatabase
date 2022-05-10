package com.example.roomdatabase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.UserListModel
import com.example.roomdatabase.databinding.UserRecyclerviewBinding
import com.example.roomdatabase.ui.UpdateUserActivity
import com.example.roomdatabase.viewModel.UserViewModel

class userlistAdapter:RecyclerView.Adapter<userlistAdapter.ViewHolder>()
{
    private var userList = emptyList<UserListModel>()
    class ViewHolder(var binding:UserRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)
    {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userlistAdapter.ViewHolder
    {
        var layoutInflater=LayoutInflater.from(parent.context)
        var binding:UserRecyclerviewBinding=DataBindingUtil.inflate(layoutInflater, R.layout.user_recyclerview, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: userlistAdapter.ViewHolder, position: Int)
    {
        var getSet=userList[position]
        holder.binding.tvName.setText(getSet.Name)
        holder.binding.tvAge.setText(getSet.Age)
        holder.binding.tvDesignation.setText(getSet.Designation)
        holder.binding.ivEdit.setOnClickListener{

            val intent= Intent(holder.itemView.context, UpdateUserActivity::class.java)
            intent.putExtra("data", getSet)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int
    {
        return userList.size
    }

    fun setData(user: List<UserListModel>) {
        this.userList = user
        notifyDataSetChanged()
    }
}