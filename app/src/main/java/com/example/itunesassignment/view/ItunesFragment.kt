package com.example.itunesassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesassignment.databinding.SongListLayoutBinding
import com.example.itunesassignment.model.remote.ItunesResponse

class ItunesFragment : Fragment() {

    companion object {
        const val DISPLAY_SONG = "DISPLAY_SONG"
        fun newInstance(itunesResponse: ItunesResponse): ItunesFragment{
            val fragment = ItunesFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY_SONG, itunesResponse)
            fragment.arguments = bundle
            return fragment
        }
    }
    private lateinit var binding: SongListLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongListLayoutBinding.inflate(layoutInflater)
        initViews()
        arguments?.getParcelable<ItunesResponse>(DISPLAY_SONG)?.let {
            updateAdapter(it)
        }
        return binding.root
    }

    private fun updateAdapter(dataSet: ItunesResponse) {
        binding.rvSongList.adapter   =  ResponseAdapter(dataSet.results)
    }

    private fun initViews(){
        binding.rvSongList.layoutManager = LinearLayoutManager(context)
    }

}