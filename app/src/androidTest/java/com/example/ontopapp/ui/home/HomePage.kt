package com.example.ontopapp.ui.home

import com.example.ontopapp.R
import com.example.ontopapp.page.Page
import com.example.ontopapp.page.checkIfViewIsDisplayed
import com.example.ontopapp.page.clickBottomNavigationViewItem
import com.example.ontopapp.page.clickInRecyclerItem
import com.example.ontopapp.page.verifyItemTextInRecyclerView
import com.example.ontopapp.page.verifyTextInChild
import com.example.ontopapp.page.verifyTextInView

class HomePage : Page() {

    fun clickInProgramRecyclerItem(recyclerId: Int, itemPosition: Int): HomePage {
        clickInRecyclerItem(recyclerId, itemPosition)
        return this
    }

    fun verifyToolbarTitle(title: String): HomePage {
        verifyTextInChild(R.id.movieDetailToolbar, title)
        return this
    }

    fun verifyTitle(title: String): HomePage {
        verifyTextInView(R.id.movie_title, title)
        return this
    }

    fun verifyReleaseDateTitle(releaseDate: String): HomePage {
        verifyTextInView(R.id.movie_release_date, releaseDate)
        return this
    }

    fun verifyVoteCount(voteCount: String): HomePage {
        verifyTextInView(R.id.movie_vote_count, voteCount)
        return this
    }

    fun verifyVoteAverage(voteAverage: String): HomePage {
        verifyTextInView(R.id.movie_vote_average, voteAverage)
        return this
    }

    fun verifyOverview(overview: String): HomePage {
        verifyTextInView(R.id.movie_detail_summary, overview)
        return this
    }

    fun checkIfToolBarIsDisplayed(): HomePage {
        checkIfViewIsDisplayed(R.id.movieDetailToolbar)
        return this
    }

    fun verifyProgramTitleInRecycler(recyclerId: Int, title: String): HomePage {
        verifyItemTextInRecyclerView(recyclerId, title)
        return this
    }

    fun clickNavigationButton(buttonId: Int): HomePage {
        clickBottomNavigationViewItem(buttonId)
        return this
    }
}
