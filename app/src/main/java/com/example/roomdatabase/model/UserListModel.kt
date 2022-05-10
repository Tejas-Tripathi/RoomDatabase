package com.example.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table") // User Entity represents a table within the database.
data class UserListModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var Name:String,
    var Designation:String,
    var Age:String)

    : Parcelable
{}
