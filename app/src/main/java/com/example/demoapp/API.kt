package com.example.demoapp

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST("apps/list")
    fun getUserAppDetails(@Field("kid_id") kidId: Long = 378): Call<UserAppData>
}
