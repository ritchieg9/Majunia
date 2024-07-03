package com.example.mazapan.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.majunia.R
import com.example.majunia.ui.model.Channels

class ChannelsAdapters(var context: Context, var arraList: ArrayList<Channels>) :
    RecyclerView.Adapter<ChannelsAdapters.itemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemsHolder {
        var itemsHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_list_item, parent, false)
        return itemsHolder(itemsHolder)
    }

    override fun getItemCount(): Int {
        return arraList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: itemsHolder, position: Int) {
       var channelItem:Channels = arraList.get(position)

//        holder.icons.setImageResource(channelItem.channelIcon!!)
        holder.channelName.text = channelItem.channelName

        holder.itemView.setOnClickListener { v ->
            notifyItemChanged(holder.getAdapterPosition())
//            v.setBackgroundColor(R.color.white)
            Toast.makeText(context,channelItem.channelName, Toast.LENGTH_LONG).show()
        }
    }

    class itemsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var icons= itemView.findViewById<ImageView>(R.id.icon_image)
        var channelName = itemView.findViewById<TextView>(R.id.tex_view)
    }
}