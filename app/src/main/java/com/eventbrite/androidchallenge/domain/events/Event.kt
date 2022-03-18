package com.eventbrite.androidchallenge.domain.events

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

data class Event(
    val id: String,
    val name: String,
    @SerializedName("start_date")
    val startDate: Date,
    val image: Image? = null
)

data class Events(
    val events: List<Event>
)

data class Image(
    val url: URL
)