package com.eventbrite.androidchallenge.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.eventbrite.androidchallenge.data.events.EventsRepository
import com.eventbrite.androidchallenge.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class EventsViewModel(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    fun fetchEvents() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(eventsRepository.getOrganizerEvents()))
        }catch(e:Exception){
            emit(Resource.Failure(e))
        }
    }

}