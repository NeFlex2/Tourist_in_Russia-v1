package com.example.tourist_in_russia_v1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tourist_in_russia_v1.databinding.AboutActivityBinding
import com.example.tourist_in_russia_v1.databinding.TyrActivityBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class About : AppCompatActivity() {
    private lateinit var binding: AboutActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AboutActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}