package com.nischal.newsapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.nischal.newsapp.R
import com.nischal.newsapp.databinding.ActivityMainBinding
import com.nischal.newsapp.ui.newsDetail.NewsDetailsFragment
import com.nischal.newsapp.ui.newsList.NewsListFragment
import com.nischal.newsapp.ui.newsList.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NewsListFragment>(R.id.fragment_container_view)
            }
        }
        initObservers()
    }

    private fun initObservers() {
        newsViewModel.newsItemClickEvent.observe(this) {
            val bundle = bundleOf(NewsDetailsFragment.SELECTED_NEWS_ITEM to it)
            supportFragmentManager.commit {
                replace<NewsDetailsFragment>(R.id.fragment_container_view, args = bundle)
                setReorderingAllowed(true)
                addToBackStack(NewsListFragment.TAG)
            }
        }
    }
}