package com.example.demoapp.repo

import com.example.demoapp.API
import com.example.demoapp.ApiResult
import com.example.demoapp.Data
import com.example.demoapp.RestClient
import javax.inject.Inject

class CommonRepository @Inject constructor() {

    private val apiService = RestClient.getInstance().create(API::class.java)

    fun getUserData() : ApiResult<Data?>{

        val response = apiService.getUserAppDetails().execute()
        if (response.isSuccessful) {
        response.body()?.data.let {
                return ApiResult.Success(it)
            }
        }
        else {
            return ApiResult.Error(response.message())
        }
    }

}