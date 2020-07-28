package com.example.pictureboardapp.activity_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureboardapp.entity.ImageResponse
import com.example.pictureboardapp.network.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class MainViewModel(private val repository : ImageRepository) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val imageLiveData = MutableLiveData<Call<ImageResponse>>()

    fun getImages() {
        coroutineScope.launch {
            val result = repository.getImageService().getSend("/200/200")
            imageLiveData.postValue(result)
        }
    }
}