package com.eventbrite.androidchallenge.data.events

import com.eventbrite.androidchallenge.domain.events.Events
import retrofit2.http.GET

interface EventsService {

    @GET("destination/organizers/22699500963/events/?expand=image&token=QGSCFRJYOKAA7IDDPMON")
    suspend fun listOrganizerEvents(): Events
}