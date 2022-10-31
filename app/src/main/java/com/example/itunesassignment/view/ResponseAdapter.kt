package com.example.itunesassignment.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.provider.MediaStore.Audio
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesassignment.databinding.SongCardLayoutBinding
import com.example.itunesassignment.model.remote.ItunesInfo
import com.example.itunesassignment.model.remote.ItunesResponse
import com.squareup.picasso3.Picasso

class ResponseAdapter(private val dataSet: List<ItunesInfo>):
    RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {

    class ResponseViewHolder(private val binding: SongCardLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(itunesItem: ItunesInfo){
            Log.d("testing", "bind: binding the info to the view holder")
            binding.root.setOnClickListener{
                val mediaPlayer =  MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(itunesItem.previewUrl)
                    prepare()
                    start()
                }
            }
            binding.tvArtistName.text = itunesItem.artistName
            binding.tvCollectionName.text = itunesItem.collectionName
            Picasso.Builder(binding.root.context).build().load(itunesItem.artworkUrl60).into(binding.ivArtwork)
            binding.tvTrackPrice.text = itunesItem.trackPrice.toString() + " USD"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        Log.d("testing", "onCreateViewHolder: creating the view holder")
        return ResponseViewHolder(
            SongCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        Log.d("testing", "onBindViewHolder: binding the view holder")
        holder.bind(dataSet[position])
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }
}