package com.example.retrofitforecaster

import retrofit2.Call
import retrofit2.http.*
interface RetrofitServices {
    @GET("forecast?q=Shklov&appid=559a014acf069376bfe96d4085734259&units=metric&lang=ru")
    fun getWeatherList(): Call<DataWeather>
}