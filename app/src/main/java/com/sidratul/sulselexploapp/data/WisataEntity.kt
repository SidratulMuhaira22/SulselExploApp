package com.sidratul.sulselexploapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tour")
data class WisataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    val name: String,
    val description: String,
    val location: String,
    val photo: Int,
    val rating: Double,
    val totalReview: Int,
    var isFavorite: Boolean = false,
)