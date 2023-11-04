package com.sidratul.sulselexploapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WisataEntity::class], version = 1, exportSchema = false)
abstract class WisataDB : RoomDatabase() {
    abstract fun wisataDao(): WisataDao
}