package com.example.demoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.web.ApiResult
import com.example.demoapp.model.Data
import com.example.demoapp.repo.CommonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KidsAppDataViewModel @Inject constructor(
    private val repo: CommonRepository
) : ViewModel() {

    private val _kidsAppData = MutableStateFlow<ApiResult<Data?>?>(null)
    val kidsAppData: Flow<ApiResult<Data?>?> get() = _kidsAppData

    fun getKidsAppData() {
        viewModelScope.launch {
            repo.getUserData().collect { result ->
                _kidsAppData.value = result
            }
        }
    }

    fun clearKidsAppData() {
        _kidsAppData.value = null
    }
}
