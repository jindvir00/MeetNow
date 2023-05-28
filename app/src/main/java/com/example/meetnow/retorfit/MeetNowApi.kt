package com.example.meetnow.retorfit

import com.example.meetnow.model.LoginModel
import com.example.meetnow.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MeetNowApi{

    @POST("login")
    fun getUser(@Body map: HashMap<String,String>): Call<LoginModel>

    @POST("register")
    fun addUser(@Body map: HashMap<String,String>): Call<RegisterModel>
}
