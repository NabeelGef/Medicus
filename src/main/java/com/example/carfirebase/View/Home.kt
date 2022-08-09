package com.example.carfirebase.View

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carfirebase.Controller.Adapter_Report
import com.example.carfirebase.Model.API
import com.example.carfirebase.Model.CONSTANT
import com.example.carfirebase.Model.ReportList
import com.example.carfirebase.R
import com.example.carfirebase.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home : AppCompatActivity() {
    lateinit var adapterReport: Adapter_Report
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       init()
        setContentView(binding.root)
        getData()
        RertyConnection()

    }

    private fun RertyConnection() {
    binding.viewNoConnection.retryConnection.setOnClickListener(View.OnClickListener {
        view ->
        binding.viewNoConnection.root.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
        getData()
    })
    }

    private fun getData() {
     val api : API = CONSTANT.CREATING_CALL()
        val callReportList:Call<ArrayList<ReportList>> = api.getBiomarkers()
        callReportList.enqueue(object : Callback<ArrayList<ReportList>> {
            override fun onResponse(
                call: Call<ArrayList<ReportList>>,
                response: Response<ArrayList<ReportList>>
            ) {
                if (response.isSuccessful) {
                    binding.progress.visibility = View.GONE
                    setAdapter(response.body())
                } else {
                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onFailure(call: Call<ArrayList<ReportList>>, t: Throwable) {
                binding.progress.visibility = View.GONE
                binding.viewNoConnection.root.visibility = View.VISIBLE

            }
        })
    }

    private fun setAdapter(dataReport: ArrayList<ReportList>?) {
     var tempArrayList : ArrayList<ReportList> = ArrayList()
        dataReport?.forEach {
          if(!it.date.isNullOrEmpty()){
              tempArrayList.add(it)
          }
        }
        adapterReport = Adapter_Report(tempArrayList!!)
        binding.recyclerReport.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
        binding.recyclerReport.adapter = adapterReport
    }

    private fun init(){
        binding = ActivityHomeBinding.inflate(layoutInflater)

    }

}