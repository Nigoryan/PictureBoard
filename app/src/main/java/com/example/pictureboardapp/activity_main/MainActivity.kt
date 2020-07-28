package com.example.pictureboardapp.activity_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pictureboardapp.R
import com.example.pictureboardapp.adapter.RecyclerAdapter
import com.example.pictureboardapp.entity.ImageResponse
import com.example.pictureboardapp.network.ImageRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call

class MainActivity : AppCompatActivity() , RecyclerAdapter.OnItemClick {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = MainViewModel(ImageRepository)
        viewModel.imageLiveData.observe(this,observer)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerImage.layoutManager = GridLayoutManager(this, 7)
        recyclerImage.adapter = recyclerAdapter
        viewModel.getImages()
        btnUpdate.setOnClickListener {

        }

    }

    private val observer = Observer<Call<ImageResponse>> { it ->
        for(i in 1..20){
            recyclerAdapter.list.add(it)
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun OnItemClicked(position: Int) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
    }
}