package com.example.hoteltest.presentation.room

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Resource
import com.example.hoteltest.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : Fragment(R.layout.fragment_room) {

    private val vm by viewModel<RoomViewModel>()
    private lateinit var roomAdapter: RoomRecyclerAdapter
    lateinit var roomRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomAdapter = RoomRecyclerAdapter()
        roomRecyclerView = view.findViewById(R.id.rv_room)
        roomRecyclerView. layoutManager = LinearLayoutManager(requireContext())
        roomRecyclerView.adapter = roomAdapter

        vm.roomData.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    roomAdapter.setData(it.data!!)
                    Log.d("test success", it.data!!.rooms[0].name)
                }
                is Resource.Error -> {
                    Log.d("test failed", "Error")
                }
                is Resource.Loading -> {
                    Log.d("test loading", "loading")
                }
            }
        }
        vm.getRoomData()

    }

}