package com.example.catfacts

import com.example.catfacts.ui.theme.Data
import com.example.catfacts.ui.theme.fact
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

val BASE_URL ="https://catfact.ninja"
interface serviceInterface{
    @GET("/facts")

    fun getfacts(@Query("page")current_page:Int): Call<fact>
}
object service{
    val serviceInstance:serviceInterface
    init{
        val retrofit = Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build()
        serviceInstance=retrofit.create(serviceInterface::class.java)
    }
}