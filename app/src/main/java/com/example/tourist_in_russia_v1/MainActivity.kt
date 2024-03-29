package com.example.tourist_in_russia_v1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
    private val listCities = mutableListOf<String>()
    lateinit var binding: ActivityMainBinding
    private val adapter = CityAdapter(this)

    lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference
        getListCities()
        init()

    }

    private fun init() {
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = adapter
            about.setOnClickListener {
                val intent = Intent(this@MainActivity, About::class.java)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getListCities() {


        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.child("Cities1").children) {
                    val city =
                        ds.getValue(
                            City::class.java
                        )
                    if (city != null) {
                        adapter.addCity(city)
                    }

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("TAG", databaseError.getMessage())
            }
        }
        database.addListenerForSingleValueEvent(valueEventListener)
    }
}

