package com.example.ontopapp.page

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.ontopapp.ui.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

open class BaseUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Before
    open fun setup() {
        hiltRule.inject()
    }
}
