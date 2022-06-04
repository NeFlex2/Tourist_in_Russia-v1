package com.example.tourist_in_russia_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tourist_in_russia_v1.databinding.TyrItemBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList

class TyrAdapter : RecyclerView.Adapter<TyrAdapter.TyrHolder>() {
    val tyrList = ArrayList<Tyr>()

    inner class TyrHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val binding = TyrItemBinding.bind(item)
        fun bind(tyr: Tyr) = with(binding)
        {
            // картинка использовать Picassoo URL imageTyr
            Picasso.get().load(tyr.imageId).placeholder(androidx.appcompat.R.drawable.abc_list_selector_holo_dark).error(R.drawable.image1).into(imageTyr)
            name.text = tyr.tyrName
            descriptions.text = tyr.tyrInfo
            
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TyrHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tyr_item, parent, false)
        return TyrHolder(view)

    }

    override fun onBindViewHolder(holder: TyrHolder, position: Int) {
        holder.bind(tyrList[position])
    }


    override fun getItemCount(): Int {
        return tyrList.size
    }

    fun addTyr(tyr: Tyr) {
        tyrList.add(tyr)
        notifyDataSetChanged()
    }

}
