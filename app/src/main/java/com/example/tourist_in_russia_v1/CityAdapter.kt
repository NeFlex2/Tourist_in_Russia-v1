package com.example.tourist_in_russia_v1

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tourist_in_russia_v1.databinding.CityItemBinding
import com.example.tourist_in_russia_v1.databinding.TyrItemBinding
import java.util.ArrayList

class CityAdapter constructor(val context: Context) :
    RecyclerView.Adapter<CityAdapter.CityHolder>() {
    var CitiesList = ArrayList<City>()


    inner class CityHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = CityItemBinding.bind(item)
        fun bind(city: City) = with(binding)
        {
            nameCity.text = city.name
            nameCity.setOnClickListener {
                Log.v("CLICK", "++++")
                val click_city: String = nameCity.text.toString()
                Log.v("CLICK",click_city)
                val intent = Intent(context, TyrActivity::class.java)
                intent.putExtra("nameCity", click_city)
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

    fun addCity(city: City) {
        CitiesList.add(city)
        notifyDataSetChanged()
    }


}
