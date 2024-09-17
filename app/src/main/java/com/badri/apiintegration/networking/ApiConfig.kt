package com.badri.apiintegration.networking

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.badri.apiintegration.model.JokesData
import com.badri.apiintegration.utils.commonUtils.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getJokes(context: Context, callback: (JokesData) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jokes = service.getJokes()
                CoroutineScope(Dispatchers.Main).launch{
                    Log.i("JokesData", "$jokes")
                    callback(jokes)
                }
            }
            catch(e:Exception){
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "API CALL FAILED", Toast.LENGTH_SHORT).show()
                }
                Log.e("CantFindService", "$e")
            }
        }

    }
}