package com.sidratul.sulselexploapp.repository

import javax.inject.Singleton
import javax.inject.Inject
import com.sidratul.sulselexploapp.data.WisataDao
import com.sidratul.sulselexploapp.data.WisataEntity

@Singleton
class WisataRepository @Inject constructor(private val tourDao: WisataDao) {
    fun getAllTour() = tourDao.getAllTour()
    fun getAllFavoriteTour() = tourDao.getAllFavoriteTour()
    fun getTour(id: Int) = tourDao.getTour(id)
    fun searchTour(query: String) = tourDao.searchTour(query)
    suspend fun insertAllTour(tour: List<WisataEntity>) = tourDao.insertAllTour(tour)
    suspend fun updateFavoriteTour(id: Int, isFavorite: Boolean) = tourDao.updateFavoriteTour(id, isFavorite)
}