package com.example.vod.di

import android.content.Context
import com.example.data.repository.VodRepositoryImpl
import com.example.domain.repository.VodRepository

class Dependency {
    fun getVodRepository(context: Context) : VodRepository {
        val vodRepo = VodRepositoryImpl(context)
        return vodRepo
    }
}