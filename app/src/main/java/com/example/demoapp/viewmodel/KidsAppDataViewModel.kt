package com.example.demoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.ApiResult
import com.example.demoapp.Data
import com.example.demoapp.repo.CommonRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KidsAppDataViewModel @Inject constructor(
    private val repo: CommonRepository
) : ViewModel() {

    private val _kidsAppData = MutableLiveData<ApiResult<Data?>?>()
    val kidsAppData: LiveData<ApiResult<Data?>?> get() = _kidsAppData

    fun getKidsAppData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repo.getUserData() }
            _kidsAppData.postValue(result)
        }
    }
}
