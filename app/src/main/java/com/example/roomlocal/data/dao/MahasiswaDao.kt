package com.example.roomlocal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomlocal.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    fun getAllMhs(): Flow<List<Mahasiswa>>

    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMhs(nim: String): Flow<Mahasiswa?>

    @Delete
    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMhs(mahasiswa: Mahasiswa)
}