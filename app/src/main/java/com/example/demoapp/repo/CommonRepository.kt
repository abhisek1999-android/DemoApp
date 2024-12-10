package com.example.demoapp.repo

import android.util.Log
import com.example.demoapp.web.API
import com.example.demoapp.web.ApiResult
import com.example.demoapp.model.Data
import com.example.demoapp.web.RestClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommonRepository @Inject constructor() {

    private val apiService = RestClient.getInstance().create(API::class.java)
    private val TAG = "CommonRepository"

    fun getUserData(): Flow<ApiResult<Data?>> = flow {
        val response = apiService.getUserAppDetails()
        if (response.isSuccessful) {
            response.body()?.data.let {
                emit(ApiResult.Success(it))
            }
        } else {
            Log.e(TAG, "getUserData: ${response.message()}")
            emit(ApiResult.Error("Something went wrong!"))
        }
    }.catch { e ->
        emit(ApiResult.Error("Something went wrong!"))
    }

}