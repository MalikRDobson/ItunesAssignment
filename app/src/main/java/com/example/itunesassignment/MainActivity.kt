package com.example.itunesassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.itunesassignment.databinding.ActivityMainBinding
import com.example.itunesassignment.model.remote.ItunesNetwork
import com.example.itunesassignment.model.remote.ItunesResponse
import com.example.itunesassignment.view.Communicator
import com.example.itunesassignment.view.ItunesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
   // private lateinit var  swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getMusic(genre = "rock")
       // swipeRefresh()
    }
    /*
    private fun swipeRefresh() {
        swipeRefresh = findViewById(R.id.swipe_refresh)

        swipeRefresh.setOnRefreshListener {
            Toast.makeText(this, "this has refreshed", Toast.LENGTH_SHORT).show()
            swipeRefresh.isRefreshing = false
        }
    }*/

    private fun initViews() {
        tabLayout = findViewById(R.id.tl_music)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("testing", "onTabSelected: getting the music")
                when(tab?.position){
                    0 ->{getMusic("rock")}
                    1 ->{getMusic("classick")}
                    2 ->{getMusic("pop")}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    override fun getMusic(genre: String) {
        Log.d("testing", "getMusic:  get the music from retrofit")
        ItunesNetwork.itunesAPI.getMusic(genre).enqueue(
            object : Callback<ItunesResponse> {
                override fun onResponse(
                    call: Call<ItunesResponse>,
                    response: Response<ItunesResponse>
                ) {
                    Log.d("testing", "onResponse: trying to get a response")
                    if(response.isSuccessful){
                        Log.d("testing", "onResponse: $response.code()")
                        Log.d("testing", "onResponse: response successful")
                        val body = response.body()
                        createDisplayFragment(body)
                    }
                    else{
                        Log.d("testing", "onResponse: $response.code()")
                        Log.d("testing", "onResponse: response failed")
                    }
                }
                override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                }
            }
        )
    }

    private fun createDisplayFragment(body: ItunesResponse?) {
        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.song_list_container, ItunesFragment.newInstance(it))
                .commit()
        }
    }
}