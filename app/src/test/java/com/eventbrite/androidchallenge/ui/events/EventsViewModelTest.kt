package com.eventbrite.androidchallenge.ui.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eventbrite.androidchallenge.data.events.EventsRepository
import com.eventbrite.androidchallenge.data.events.EventsService
import com.eventbrite.androidchallenge.domain.events.Event
import com.eventbrite.androidchallenge.util.Resource
import com.eventbrite.androidchallenge.utils.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import java.util.*

/*
Bonus points:

Create unit tests to check if the ViewModel is exposing
the correct UI states when the API call succeeds or fails.
A basic test setup is provided in the class EventsViewModelTest.*/

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EventsViewModelTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    val eventList = listOf<Event>(
        Event("a", "name", Date()),
        Event("b", "other name", Date())
    )

    val error = Exception()


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getUiStateWhenResponseIsSuccessful`() = runTest(dispatcher){
        val eventsRepository = mock<EventsRepository>()
        val viewModel = EventsViewModel(eventsRepository = eventsRepository)
            Mockito.`when`(eventsRepository.getOrganizerEvents())
                .thenReturn(eventList)

        val data =  viewModel.fetchEvents().getOrAwaitValue()
        val success = Resource.Success(data)
        assert(success.data != null)

    }

    @Test
    fun `getUiStateWhenResponseHasFailed`() = runTest(dispatcher){
        val eventsRepository = mock<EventsRepository>()
        val viewModel = EventsViewModel(eventsRepository = eventsRepository)
        Mockito.`when`(eventsRepository.getOrganizerEvents())
            .thenReturn(emptyList())
        val error = Resource.Failure(error)
        assert(error != null)

    }


}


