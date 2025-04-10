package com.example.domain.repository

import com.example.domain.model.VodModel

interface VodRepository {
    suspend fun getVod(): VodModel
}

