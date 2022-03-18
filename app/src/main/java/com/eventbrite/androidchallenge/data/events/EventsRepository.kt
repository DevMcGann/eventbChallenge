package com.eventbrite.androidchallenge.data.events

import com.eventbrite.androidchallenge.domain.events.Event
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventsRepository {

    suspend fun getOrganizerEvents(): List<Event> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.evbqaapi.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val eventsService = retrofit.create(EventsService::class.java)

        return eventsService.listOrganizerEvents().events
    }
}