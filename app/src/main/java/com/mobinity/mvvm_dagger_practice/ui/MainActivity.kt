package com.mobinity.mvvm_dagger_practice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import com.mobinity.mvvm_dagger_practice.R
import com.mobinity.mvvm_dagger_practice.model.Blog
import com.mobinity.mvvm_dagger_practice.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }

        }
    }

    private fun displayError(message: String?) {
        if(message != null) {
            findViewById<TextView>(R.id.text).text = message
        }
        else {
            findViewById<TextView>(R.id.text).text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        findViewById<TextView>(R.id.text).text = sb.toString()
    }

}







