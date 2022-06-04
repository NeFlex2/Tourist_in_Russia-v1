package com.example.tourist_in_russia_v1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourist_in_russia_v1.databinding.ActivityMainBinding
import com.example.tourist_in_russia_v1.databinding.CityItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val listTyr = mutableListOf<Tyr>()
    private val listCities = mutableListOf<String>()
    lateinit var binding: ActivityMainBinding
    private val adapter = CityAdapter(this)
    val imglistTyr = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // костыли |
        database = Firebase.database.reference
        writeNewTyr(
            "Сочи",
            "0",
            "SochiTyr1",
            "Descriptions",
            "https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/29b3acc9-42fb-4b42-8a2a-ff371d2a6066/-/format/jpeg/-/quality/lighter/-/stretch/off/-/resize/1900x/"
        )
        getTyr("Города", "Москва", "1")
        getTyr("Города", "Сочи", "1")
        getListCities()
// Firabase костыли ..

        init()

    }

    private fun init() {
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = adapter

        }


    }

    fun writeNewTyr(
        nameCity: String,
        tyrId: String,
        tyrName: String,
        tyrInfo: String,
        imageUrl: String
    ) {
        val tyr = Tyr(tyrName = tyrName, tyrInfo = tyrInfo, imageId = imageUrl)
        database.child("Города").child(nameCity).child(tyrId).setValue(tyr)
    }

    fun getTyr(frstChild: String, nameCity: String, tyrId: String) {

        database.child(frstChild).child(nameCity).child(tyrId).child("tyrName").get()
            .addOnSuccessListener { data ->
                val nameTyr = data.value.toString()
                Log.w("myTag", nameTyr)
            }
        database.child(frstChild).child(nameCity).child(tyrId).child("tyrInfo").get()
            .addOnSuccessListener { data ->
                val infoTyr = data.value.toString()
                Log.w("myTag", infoTyr)
            }
        database.child(frstChild).child(nameCity).child(tyrId).child("imageId").get()
            .addOnSuccessListener { data ->
                val imageUrl = data.value.toString()
                Log.w("myTag", imageUrl)
            }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getListCities() {

        database.child("Cities").child("0").get().addOnSuccessListener { data ->
            val city = data.value.toString()
            Log.w("myTag", city)
        }

        database.child("Города").get().addOnSuccessListener { data ->
            val city = data.value
            Log.v("TAG", city.toString())


        }
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.v("snap", dataSnapshot.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        database.child("Cities").get().addOnSuccessListener { data ->
            val city = data.value as List<String>
            Log.v("myTag", city.joinToString())
            adapter.CitiesList = city.map {
                City(it)
            }.toMutableList() as ArrayList<City>
            adapter.notifyDataSetChanged()
        }


    }


}

