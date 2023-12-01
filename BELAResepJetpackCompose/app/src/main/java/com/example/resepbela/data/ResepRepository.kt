package com.example.resepbela.data

import com.example.resepbela.model.ResepData
import com.example.resepbela.model.Resep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ResepRepository {
    private val dummyResep = mutableListOf<Resep>()

    init {
        if (dummyResep.isEmpty()) {
            ResepData.dummyResep.forEach {
                dummyResep.add(it)
            }
        }
    }

    fun getResepById(resepId: Int): Resep {
        return dummyResep.first {
            it.id == resepId
        }
    }

    fun getFavoriteResep(): Flow<List<Resep>> {
        return flowOf(dummyResep.filter { it.isFavorite })
    }

    fun searchResep(query: String) = flow {
        val data = dummyResep.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateResep(resepId: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyResep.indexOfFirst { it.id == resepId }
        val result = if (index >= 0) {
            val resep = dummyResep[index]
            dummyResep[index] = resep.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: ResepRepository? = null

        fun getInstance(): ResepRepository =
            instance ?: synchronized(this) {
                ResepRepository().apply {
                    instance = this
                }
            }
    }
}