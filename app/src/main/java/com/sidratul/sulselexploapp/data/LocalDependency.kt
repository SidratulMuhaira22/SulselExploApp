package com.sidratul.sulselexploapp.data

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDependency {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, WisataDB::class.java, "sulselexploapp.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: WisataDB) = database.wisataDao()
}