package com.example.roomlocal.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocal.data.entity.Mahasiswa
import com.example.roomlocal.repository.RepositoryMhs
import com.example.roomlocal.ui.navigation.DestinasiDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs,
): ViewModel(){
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    val detailUIState: StateFlow<DetailUIState> = repositoryMhs.getMhs(_nim)
        .filterNotNull()
        .map {
            DetailUIState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .catch {
            emit(
                DetailUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?:"Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUIState(
                isLoading = true,
            )
        )
    fun deleteMhs(){
        detailUIState.value.detailUiEvent.toMahasiswaEntity().let{
            viewModelScope.launch {
                repositoryMhs.deleteMhs(it)
            }
        }
    }
}

data class DetailUIState(
    val detailUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MahasiswaEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MahasiswaEvent()
}

fun Mahasiswa.toDetailUiEvent(): MahasiswaEvent{
    return MahasiswaEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan
    )
}