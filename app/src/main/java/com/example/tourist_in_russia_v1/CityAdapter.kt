package com.example.tourist_in_russia_v1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tourist_in_russia_v1.databinding.CityItemBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class CityAdapter constructor(val context: Context) :
    RecyclerView.Adapter<CityAdapter.CityHolder>() {
    var CitiesList = ArrayList<City>()


    inner class CityHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = CityItemBinding.bind(item)
        fun bind(city: City) = with(binding)
        {
            Picasso.get().load(city.imageCity).placeholder(R.drawable.circledownload)
                .error(R.drawable.error).into(imageCity)
            nameCity.text = city.name
            nameCity.setOnClickListener {
                val click_city: String = nameCity.text.toString()
                val intent = Intent(context, TyrActivity::class.java)
                intent.putExtra("nameCity", click_city)
                context.startActivity(intent)

            }
            maps.setOnClickListener {
                val mapCity = city.name
                val map = "http://maps.google.co.in/maps?q=$mapCity";
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityHolder(view)

    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(CitiesList[position])
    }


    override fun getItemCount(): Int {
        return CitiesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCity(city: City) {
        CitiesList.add(city)
        notifyDataSetChanged()
    }
}
