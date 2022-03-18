package com.eventbrite.androidchallenge.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eventbrite.androidchallenge.data.events.EventsRepository

class EventsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            return EventsViewModel(eventsRepository = EventsRepository()) as T
        } else {
            throw IllegalArgumentException("ViewModel $modelClass not supported")
        }
    }
}