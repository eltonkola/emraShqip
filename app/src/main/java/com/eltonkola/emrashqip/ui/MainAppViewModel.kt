package com.eltonkola.emrashqip.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltonkola.emrashqip.MainApp
import com.eltonkola.emrashqip.data.Emri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainAppViewModel: ViewModel() {

    private val emriDao = MainApp.database.emriDao()


    val emriListState: MutableState<List<Emri>> = mutableStateOf(emptyList())


    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val emriList = emriDao.getAllEmri()
            emriListState.value = emriList
        }
    }

}
