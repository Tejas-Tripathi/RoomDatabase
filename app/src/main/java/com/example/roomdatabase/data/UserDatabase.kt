package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.model.UserListModel

@Database(
    entities = [UserListModel::class], version = 1,                // <- Database version
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase()
{ // <- Add 'abstract' keyword and extends RoomDatabase
    abstract fun userDao(): UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase
        {
            val tempInstance = INSTANCE

            if (tempInstance != null)
            {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, UserDatabase::class.java, "userdatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
