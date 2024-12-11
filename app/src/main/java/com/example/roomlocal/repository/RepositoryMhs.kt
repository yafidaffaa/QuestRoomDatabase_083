package com.example.roomlocal.repository

import com.example.roomlocal.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}