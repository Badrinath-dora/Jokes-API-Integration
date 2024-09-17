package com.badri.apiintegration

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.badri.apiintegration.databinding.ActivityMainBinding
import com.badri.apiintegration.networking.ApiConfig.getJokes
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        binding.btnGetJokes.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.txtJokes.text = ""
            binding.ivJokes.setImageDrawable(null)
            binding.txtJokes.background = null

            getJokes(this) { jokes ->
                Log.i("Jokes:", "$jokes")
                binding.txtJokes.text = jokes.value
                binding.txtJokes.setBackgroundResource(R.drawable.rounded_rectangle_border)
                Glide.with(this).load(jokes.icon_url).into(binding.ivJokes)
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}