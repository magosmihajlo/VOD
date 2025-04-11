package com.example.domain.repository

import com.example.domain.model.AuidModel

interface VodRepository {
    suspend fun getVod(): AuidModel
}

