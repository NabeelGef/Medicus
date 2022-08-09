package com.example.carfirebase.Model


import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("biomarkers")
    public fun getBiomarkers():Call<ArrayList<ReportList>>
}