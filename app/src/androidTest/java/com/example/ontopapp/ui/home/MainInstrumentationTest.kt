package com.example.ontopapp.ui.home

import com.example.ontopapp.R
import com.example.ontopapp.data.database.MovieDao
import com.example.ontopapp.data.database.MovieDatabase
import com.example.ontopapp.page.BaseUiTest
import com.example.ontopapp.page.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainInstrumentationTest : BaseUiTest() {

    @Inject
    lateinit var database: MovieDatabase

    @Inject
    lateinit var movieDao: MovieDao

    @Test
    fun click_a_movie_navigates_to_detail() {
        on<HomePage>()
            .wait(1)
            .on<HomePage>()
            .clickInProgramRecyclerItem(R.id.recyclerMovies, 4)
            .verifyToolbarTitle("Original Title 5")
            .verifyTitle("Title 5")
            .verifyReleaseDateTitle("01/01/2025")
            .verifyVoteCount("5")
            .verifyVoteAverage("5.1")
            .verifyOverview("Overview 5")
    }

    @Test
    fun click_a_movie_navigates_to_detail_click_back_navigates_home() {
        on<HomePage>()
            .wait(1)
            .on<HomePage>()
            .clickInProgramRecyclerItem(R.id.recyclerMovies, 4)
            .checkIfToolBarIsDisplayed()
            .back()
            .on<HomePage>()
            .verifyProgramTitleInRecycler(R.id.recyclerMovies, "Title 5")
    }

    @Test
    fun click_phrase_button_navigates_to_phrases_view() {
        on<HomePage>()
            .wait(1)
            .on<HomePage>()
            .clickNavigationButton(R.id.phrase_dest)
    }

    @Test
    fun click_password_button_navigates_to_password_view() {
        on<HomePage>()
            .wait(1)
            .on<HomePage>()
            .clickNavigationButton(R.id.password_dest)
    }
}
