package com.example.carfirebase.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CONSTANT {
    companion object{
        const val URL : String = "https://retoolapi.dev/hZZ5j8/"
        const val CATEGORY : String = "category"
        const val SYMBOL : String = "symbol"
        const val DATE : String = "date"
        const val VALUE :String = "value"
        const val INFO : String = "info"
        const val COLOR : String = "color"
        const val INSIGHT : String = "insight"
        fun CREATING_CALL(): API {
            val retrofit = Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val api: API
            api = retrofit.create(API::class.java)
            return api
        }
    }
}