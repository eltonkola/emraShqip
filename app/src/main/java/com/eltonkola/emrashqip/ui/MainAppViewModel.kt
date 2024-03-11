package com.eltonkola.emrashqip.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltonkola.emrashqip.MainApp
import com.eltonkola.emrashqip.data.Emri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainAppViewModel : ViewModel() {

    private val emriDao = MainApp.database.emriDao()

    private val _favorites = MutableStateFlow<List<Emri>>(emptyList())
    val favorites: StateFlow<List<Emri>> = _favorites.asStateFlow()

    private val _emratListAll = MutableStateFlow<List<Emri>>(emptyList())
    val allEmrat: StateFlow<List<Emri>> = _emratListAll.asStateFlow()

    private val _unvotedNames = MutableStateFlow<List<Emri>>(emptyList())
    val unvotedNames: StateFlow<List<Emri>> = _unvotedNames.asStateFlow()

    private val _lastName = MutableStateFlow("Kola")
    val lastName: StateFlow<String> = _lastName.asStateFlow()

    init {
        loadData()
    }

    fun setlastName(mbiemri: String){
        _lastName.value = mbiemri
    }

    private fun loadData() {
        viewModelScope.launch {
            emriDao.loadUnvotedNamed().collect {
                _unvotedNames.value = it
            }
        }
        viewModelScope.launch {
            emriDao.getAllData().collect {
                _emratListAll.value = it
                println("all data ${it.size}")
            }
        }
        viewModelScope.launch {
            emriDao.getFavorite().collect {
                _favorites.value = it
            }
        }
    }

    fun updateEmri(emri: Emri) {
        viewModelScope.launch() {
            println("update $emri")
            emriDao.updateEmri(emri)
        }

    }

}
