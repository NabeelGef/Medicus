package com.example.carfirebase.Controller

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.carfirebase.Model.CONSTANT
import com.example.carfirebase.Model.ReportList
import com.example.carfirebase.R
import com.example.carfirebase.View.Biomarker_Details

class Adapter_Report (reportLists : ArrayList<ReportList>) : RecyclerView.Adapter<Adapter_Report.ViewHolder>(){
    var reportLists:ArrayList<ReportList> = reportLists
    get() = field
    set(value) {
        field = value
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        fun BindItem(reportList: ReportList){
            val bundle:Bundle = Bundle()
            val goto_details : Button = itemView.findViewById(R.id.goto_details)
            val symbol : Button = itemView.findViewById(R.id.symbol)
            val date : TextView = itemView.findViewById(R.id.date)
            val value : TextView = itemView.findViewById(R.id.value)
            val info : String = reportList.info
            val insights : String = reportList.insight
            val category : String = reportList.category
            val color : String = reportList.color
            symbol.setText(reportList.symbol)
            val drawable : Drawable? = AppCompatResources.getDrawable(itemView.context,R.drawable.circle_border)
            val editDrawable : Drawable? = DrawableCompat.wrap(drawable!!)
            DrawableCompat.setTint(editDrawable!!,Color.parseColor(color))
            symbol.setBackgroundDrawable(editDrawable)
            date.setText(reportList.date)
            value.setText(reportList.value)
            goto_details.setOnClickListener(View.OnClickListener {
                    view ->
                val intent: Intent = Intent(itemView.context, Biomarker_Details::class.java)
                bundle.putString(CONSTANT.CATEGORY,category)
                bundle.putString(CONSTANT.SYMBOL,reportList.symbol)
                bundle.putString(CONSTANT.DATE,reportList.date)
                bundle.putString(CONSTANT.VALUE,reportList.value)
                bundle.putString(CONSTANT.INFO,info)
                bundle.putString(CONSTANT.COLOR,color)
                bundle.putString(CONSTANT.INSIGHT,insights)
                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(LayoutInflater.from(parent.context).
         inflate(R.layout.card_report_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!reportLists.get(position).date.equals(null)){
            holder.BindItem(reportLists.get(position))
        }

    }

    override fun getItemCount(): Int {
       return reportLists.size
     }
}