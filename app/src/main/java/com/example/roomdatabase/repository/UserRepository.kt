package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.data.UserDao
import com.example.roomdatabase.model.UserListModel

class UserRepository(private val userDao: UserDao)
{
    val readAllData: LiveData<List<UserListModel>> = userDao.readAllData()

    suspend fun addUser(user: UserListModel) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: UserListModel) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserListModel) {
        userDao.deleteUser(user)
    }

    /*suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }*/

}