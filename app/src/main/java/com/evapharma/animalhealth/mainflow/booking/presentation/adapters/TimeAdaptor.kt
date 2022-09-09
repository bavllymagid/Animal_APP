package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.TimeItemBinding

class TimeAdaptor(private val timeList: ArrayList<String>, val context: Context) :
    BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private var selectedPosition = -14245
    lateinit var binding: TimeItemBinding


    override fun getCount(): Int {
        return timeList.size
    }

    override fun getItem(position: Int): Any {
        return timeList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        binding = TimeItemBinding.inflate(layoutInflater!!)

        binding.timeTv.text = timeList[position]


        return binding.root
    }
}
