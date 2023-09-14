package com.example.hoteltest.presentation.room

import Room
import RoomModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hoteltest.R
import com.google.android.material.button.MaterialButton

class RoomRecyclerAdapter: RecyclerView.Adapter<RoomRecyclerAdapter.ItemsViewHolder>()  {

    lateinit var clickListener: OnItemClickListener

    private var roomsList = emptyList<Room>()

    inner class ItemsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val roomName = itemView.findViewById<TextView>(R.id.tv_name_room_item)
        val roomPrice = itemView.findViewById<TextView>(R.id.tv_room_price)
        val roomPricePer = itemView.findViewById<TextView>(R.id.tv_room_pricePer)
        val flow = itemView.findViewById<androidx.constraintlayout.helper.widget.Flow>(R.id.flow_room_item)
        val button = itemView.findViewById<MaterialButton>(R.id.btn_to_booking)
        init {
            button.setOnClickListener{
                if (clickListener != null){
                    clickListener.onItemClick(roomsList[bindingAdapterPosition])
                }
            }
        }

        fun bind(room: Room){
            roomName.text = room.name
            roomPrice.text = room.price.toString()
                .replace("(\\d)(?=(\\d{3})+$)".toRegex(), "$1 ").plus(" ₽")
            roomPricePer.text = room.price_per.replaceFirstChar {
                it.lowercaseChar()
            }
            room.peculiarities.forEachIndexed { i, str ->
                val peculiaritiesView =
                    LayoutInflater.from(itemView.context).inflate(R.layout.peculiarities_item, null)
                peculiaritiesView.id = View.generateViewId()
                val tv = peculiaritiesView.findViewById<TextView>(R.id.tv_peculiarity)
                tv.text = str
                itemView.findViewById<ConstraintLayout>(R.id.constraint_peculiarities_room)
                    .addView(peculiaritiesView, i)
                flow.addView(peculiaritiesView)

                val imageList = ArrayList<SlideModel>() // Create image list

                room.image_urls.forEach { s ->
                    imageList.add(SlideModel(s, ScaleTypes.CENTER_CROP))
                }

                val imageSlider = itemView.findViewById<ImageSlider>(R.id.image_slider_room_item)
                imageSlider.setImageList(imageList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.room_recycler_item, parent, false)
        return ItemsViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = roomsList.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(roomsList[position])
    }

    fun setData(data: RoomModel){
        val newList = mutableListOf<Room>()
        data.rooms.forEach{
            newList.add(it)
        }
        roomsList = newList
        notifyDataSetChanged()
    }


    interface OnItemClickListener{
        fun onItemClick(room: Room)
    }
    fun setOnClickListener(onClickListener: OnItemClickListener){
        this.clickListener = onClickListener
    }

}