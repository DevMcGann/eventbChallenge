package com.eventbrite.androidchallenge.ui.events

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventbrite.androidchallenge.R
import com.eventbrite.androidchallenge.databinding.EventsFragmentBinding
import com.eventbrite.androidchallenge.util.Resource

class EventsFragment : Fragment(R.layout.events_fragment) {

    private val viewModel: EventsViewModel by viewModels(factoryProducer = { EventsViewModelFactory() })
    lateinit var binding : EventsFragmentBinding
    private lateinit var  eventAdapter : EventsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventsFragmentBinding.bind(view)
        eventAdapter = EventsAdapter(emptyList())

        setView()
        observeViewModel()
    }

    private fun setView() {
       binding.rvEvents.apply {
           adapter = eventAdapter
           layoutManager = LinearLayoutManager(requireActivity())
       }
    }

    private fun observeViewModel() {
        viewModel.fetchEvents().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.pbEvents.isVisible = true
                    binding.imageView.isVisible = false
                    binding.tvError.isVisible = false
                }
                is Resource.Success -> {
                    binding.pbEvents.isVisible = false
                    binding.imageView.isVisible = false
                    binding.tvError.isVisible = false
                    Log.d("DATAAAAA", result.data.toString())
                    eventAdapter = EventsAdapter(
                        result.data,
                    )
                    binding.rvEvents.adapter = eventAdapter
                }
                is Resource.Failure -> {
                    binding.pbEvents.isVisible = false
                    binding.imageView.isVisible = true
                    binding.tvError.isVisible = true
                    Log.d("Error", "${result.exception}")
                }
            }
        })
    }


}