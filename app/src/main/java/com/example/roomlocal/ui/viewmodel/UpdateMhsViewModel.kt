package com.example.roomlocal.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlocal.data.entity.Mahasiswa
import com.example.roomlocal.repository.RepositoryMhs
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
): ViewModel(){
    var updateUIState by mutableStateOf(MhsUIState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUIState=repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUIStateMhs()
        }
    }


}

fun Mahasiswa.toUIStateMhs():MhsUIState = MhsUIState(
    mahasiswaEvent = this.toDetailUiEvent(),
)