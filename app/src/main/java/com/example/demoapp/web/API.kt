package com.example.demoapp.web

import com.example.demoapp.model.UserAppData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST("apps/list")
    suspend fun getUserAppDetails(@Field("kid_id") kidId: Long = 378): Response<UserAppData>
}
