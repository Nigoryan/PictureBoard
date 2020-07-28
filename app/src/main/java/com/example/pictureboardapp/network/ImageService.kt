package com.example.pictureboardapp.network

import com.example.pictureboardapp.entity.ImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ImageService {
    @GET("/200/200/")
    suspend fun getImages(): ImageResponse

    @GET
    fun getSend(@Url url:String) : Call<ImageResponse>
}