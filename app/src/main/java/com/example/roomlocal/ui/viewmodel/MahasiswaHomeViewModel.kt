package com.example.roomlocal.ui.viewmodel

import com.example.roomlocal.data.entity.Mahasiswa

data class HomeUIState(
    val listMhs: List<Mahasiswa> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)