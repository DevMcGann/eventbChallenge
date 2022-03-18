package com.eventbrite.androidchallenge.ui.events

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventbrite.androidchallenge.databinding.EventCardBinding
import com.eventbrite.androidchallenge.domain.events.Event
import com.eventbrite.androidchallenge.util.BaseViewHolder

class EventsAdapter (
    private val eventsList: List<Event>,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = EventsViewHolder(itemBinding, parent.context)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is EventsViewHolder -> holder.bind(eventsList[position])
        }
    }

    override fun getItemCount(): Int = eventsList.size

    private inner class EventsViewHolder(
        val binding: EventCardBinding,
        val context: Context
    ) : BaseViewHolder<Event>(binding.root) {
        override fun bind(item: Event) {
            Glide.with(context).load("${item.image?.url}")
                .centerCrop().into(binding.imgEvent)
            binding.tvTitle.text = item.name
            binding.tvStartDate.text = item.startDate.toString()
        }
    }

}