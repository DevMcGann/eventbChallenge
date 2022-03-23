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
    lateinit var viewModel : EventsViewModel
    lateinit var repository: EventsRepository

    val eventList = listOf<Event>(
        Event("a", "name", Date()),
        Event("b", "other name", Date())
    )

    val error = Exception()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = EventsRepository()
        viewModel = EventsViewModel(repository)

    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getUiStateWhenResponseIsSuccessful`() = runTest(dispatcher){
            Mockito.`when`(repository.getOrganizerEvents())
                .thenReturn(eventList)

           val result =  viewModel.fetchEvents().getOrAwaitValue()
    }


}




/*
@get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

private val dispatcher = UnconfinedTestDispatcher()

@Before
fun setup() {
    Dispatchers.setMain(dispatcher)
}

@After
fun teardown() {
    Dispatchers.resetMain()
}

@Test
fun template() = runTest(dispatcher) {
    val eventsRepository = mock<EventsRepository>()

    val viewModel = EventsViewModel(eventsRepository = eventsRepository)
}*/
