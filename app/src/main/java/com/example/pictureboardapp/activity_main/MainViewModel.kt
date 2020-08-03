package com.example.pictureboardapp.activity_main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    //val imageLiveData = MutableLiveData<Call<ImageResponse>>()

    fun getImages() {
        coroutineScope.launch {
//            val result = repository.getImageService().getSend("/200/200")
//            imageLiveData.postValue(result)
        }
    }
}