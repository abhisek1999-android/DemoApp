package com.example.demoapp

import com.google.gson.annotations.SerializedName


data class UserAppData(
    val data: Data,
    val message: String,
    val success: Boolean
)

data class Data(
    @SerializedName("app_list")
    val appList: List<App>,
    @SerializedName("usage_access")
    val usageAccess: Int
)

data class App(
    @SerializedName("app_icon")
    val appIcon: String,
    @SerializedName("app_id")
    val appId: Int,
    @SerializedName("app_name")
    val appName: String,
    @SerializedName("app_package_name")
    val appPackageName: String,
    @SerializedName("fk_kid_id")
    val fkKidId: Int,
    @SerializedName("kid_profile_image")
    val kidProfileImage: String,
    var status: String
)
