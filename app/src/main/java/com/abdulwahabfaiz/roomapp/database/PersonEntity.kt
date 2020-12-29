package com.abdulwahabfaiz.roomapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class PersonEntity(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}