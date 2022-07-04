package com.example.tourist_in_russia_v1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourist_in_russia_v1.databinding.TyrActivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class TyrActivity : AppCompatActivity() {
    private val adapter = TyrAdapter()
    lateinit var database: DatabaseReference
    private lateinit var binding: TyrActivityBinding
    lateinit var city_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TyrActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        database = Firebase.database.reference
        city_name = bundle?.getString("nameCity").toString()
        val name: String?
        name = null
        init()


    }

    private fun init() {
        binding.apply {
            recyclerTyr.layoutManager = LinearLayoutManager(this@TyrActivity)
            recyclerTyr.adapter = adapter
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.child("Города").child(city_name).children) {
                        val tyr =
                            ds.getValue(
                                Tyr::class.java
                            )
                        if (tyr != null) {
                            adapter.addTyr(tyr)
                        }

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("TAG", databaseError.getMessage()) //Don't ignore errors!
                }
            }
            database.addListenerForSingleValueEvent(valueEventListener)


        }


    }


}
