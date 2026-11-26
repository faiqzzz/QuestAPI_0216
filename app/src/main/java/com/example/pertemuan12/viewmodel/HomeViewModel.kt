package com.example.pertemuan12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.modeldata.DataSiswa
import com.example.pertemuan12.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa {

    data class Success(
        val siswa: List<DataSiswa> = listOf()
    ) : StatusUiSiswa

    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var listSiswa: StatusUiSiswa by mutableStateOf(
        StatusUiSiswa.Loading
    )
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading

            listSiswa = try {
                val data = repositoryDataSiswa.getDataSiswa()

                println("DATA SISWA BERHASIL: $data")

                StatusUiSiswa.Success(data)

            } catch (e: Exception) {
                e.printStackTrace()
                println("ERROR LOAD SISWA: ${e.message}")

                StatusUiSiswa.Error
            }
        }
    }
}