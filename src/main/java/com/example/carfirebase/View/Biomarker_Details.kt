package com.example.carfirebase.View

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.example.carfirebase.Model.CONSTANT
import com.example.carfirebase.R
import com.example.carfirebase.databinding.ActivityBiomarkerDetailsBinding
import org.w3c.dom.Text
import java.text.DateFormatSymbols

class Biomarker_Details : AppCompatActivity() {
    lateinit var bundle: Bundle
    lateinit var color : String
    lateinit var binding: ActivityBiomarkerDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(binding.root)
        getData()
        clickingInfo()
    }

    private fun clickingInfo() {
        binding.clickInfo.setOnClickListener(View.OnClickListener {
            view ->
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage(bundle.getString(CONSTANT.INFO))
            alertDialogBuilder.show()
        })
    }

    private fun getData() {
        val Resullt:String = "Your Result Is "
        binding.category.setText(bundle.getString(CONSTANT.CATEGORY))
        binding.dateDetails.setText(bundle.getString(CONSTANT.DATE))
        binding.valueDetails.setText(Resullt + bundle.getString(CONSTANT.VALUE))
        binding.symbolDetailsButton.setText(bundle.getString(CONSTANT.SYMBOL))
        color = bundle.getString(CONSTANT.COLOR)!!
        binding.symbolDetails.setText(bundle.getString(CONSTANT.SYMBOL))
        binding.infoDetails.setText(bundle.getString(CONSTANT.INFO))
        binding.insightsDetails.setText(bundle.getString(CONSTANT.INSIGHT))
        val drawable : Drawable? = AppCompatResources.getDrawable(applicationContext,R.drawable.circle_border)
        val editDrawable : Drawable? = DrawableCompat.wrap(drawable!!)
        DrawableCompat.setTint(editDrawable!!, Color.parseColor(color))
        binding.symbolDetailsButton.setBackgroundDrawable(editDrawable)
    }

    private fun init(){
        bundle = intent.extras!!
        binding = ActivityBiomarkerDetailsBinding.inflate(layoutInflater)
        }
}