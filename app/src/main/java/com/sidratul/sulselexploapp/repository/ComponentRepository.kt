package com.sidratul.sulselexploapp.repository

import com.sidratul.sulselexploapp.data.WisataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ComponentRepository {
    @Provides
    @ViewModelScoped
    fun provideRepository(wisataDao: WisataDao) = WisataRepository(wisataDao)
}