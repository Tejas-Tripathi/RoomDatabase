package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabase.model.UserListModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    suspend fun addUser(user: UserListModel)

    @Update
    suspend fun updateUser(user: UserListModel)

    @Delete
    suspend fun deleteUser(user: UserListModel)

    /*@Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()*/

    @Query("SELECT * from user_table") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<UserListModel>> // <- This means function return type is List. Specifically, a List of Users.
}