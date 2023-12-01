package com.example.resepbela.di

import com.example.resepbela.data.ResepRepository

object Injection {
    fun provideRepository(): ResepRepository {
        return ResepRepository.getInstance()
    }
}