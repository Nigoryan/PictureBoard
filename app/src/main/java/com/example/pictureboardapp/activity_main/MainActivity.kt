package com.example.pictureboardapp.activity_main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pictureboardapp.R
import com.example.pictureboardapp.adapter.PaginationScrollListener
import com.example.pictureboardapp.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() , RecyclerAdapter.OnItemClick {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val PAGE_START = 1
    private var currentPage = PAGE_START
    private val isLastPage = false
    private val totalPage = 3
    private var isLoading = false
    var itemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = MainViewModel()
        recyclerAdapter = RecyclerAdapter(this)
        val layoutManager = GridLayoutManager(this, 7)
        recyclerImage.layoutManager = layoutManager
        recyclerImage.adapter = recyclerAdapter

        btnAdd.setOnClickListener {
            recyclerAdapter.notifyItemInserted(recyclerAdapter.item_count+1)
            recyclerAdapter.item_count = recyclerAdapter.item_count+1
        }

        btnUpdate.setOnClickListener {
            recyclerAdapter.item_count = 140
            recyclerAdapter.notifyDataSetChanged()
        }

        recyclerImage.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
//                if (currentPage != PAGE_START) mPaginationAdapter.removeLoading();
//                mPaginationAdapter.addAll(response.body());
//                mSwipeRefreshLayout.setRefreshing(false);
//                if (currentPage < totalPage) mPaginationAdapter.addLoading();
//                else isLastPage = true;

                isLoading = false;
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })

    }

//    private val observer = Observer<Call<ImageResponse>> { it ->
//        recyclerAdapter.list.add(response)
//        recyclerAdapter.notifyDataSetChanged()
//    }

    override fun OnItemClicked(position: Int) {
        Toast.makeText(this, "Click $position", Toast.LENGTH_SHORT).show()
    }
}