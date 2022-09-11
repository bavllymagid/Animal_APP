package com.evapharma.animalhealth.mainflow.booking.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.databinding.TimeItemBinding
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.utils.DateConverter

class TimeAdaptor(private var timeList: ArrayList<DateTimeSlot>, val context: Context) :
    BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
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

    fun setTimeList(list: ArrayList<DateTimeSlot>){
        timeList = list
    }

    fun getTimeList():ArrayList<DateTimeSlot>{
        return timeList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        binding = TimeItemBinding.inflate(layoutInflater!!)

        binding.timeTv.text = DateConverter.stringToTime(timeList[position].startAt)


        return binding.root
    }
}
