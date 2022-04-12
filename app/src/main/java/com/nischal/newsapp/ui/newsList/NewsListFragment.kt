package com.nischal.newsapp.ui.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nischal.newsapp.R
import com.nischal.newsapp.databinding.FragmentNewsListBinding
import com.nischal.newsapp.ui.main.MainActivity
import com.nischal.newsapp.ui.newsList.adapter.NewsListAdapter
import com.nischal.newsapp.utils.gone
import com.nischal.newsapp.utils.showToast
import com.nischal.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    companion object {
        const val TAG = "NewsListFragment"
    }

    private val newsViewModel: NewsViewModel by activityViewModels()

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var binding: FragmentNewsListBinding? = null

    private val newsListAdapter by lazy {
        NewsListAdapter(newsViewModel = newsViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        newsViewModel.fetchNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                (activity as MainActivity?)?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        (activity as MainActivity?)?.supportActionBar?.run {
            title = resources.getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }

        binding?.rvNewsList?.run {
            val linearLayoutManager = LinearLayoutManager(this.context).apply {
                orientation = RecyclerView.VERTICAL
            }
            layoutManager = linearLayoutManager
            adapter = newsListAdapter
        }

        binding?.srlNewsList?.setOnRefreshListener {
            newsViewModel.fetchNews()
        }
    }

    private fun initObservers() {
        with(newsViewModel) {
            newsResult.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    binding?.rvNewsList?.visible()
                    binding?.tvErrorMessage?.gone()
                    newsListAdapter.setNewsList(it)
                } else {
                    binding?.rvNewsList?.gone()
                    binding?.tvErrorMessage?.visible()
                    binding?.tvErrorMessage?.text =
                        resources.getString(R.string.err_news_list_empty)
                    newsListAdapter.setNewsList(mutableListOf())
                }
            }

            isNewsRefreshing.observe(viewLifecycleOwner) {
                binding?.srlNewsList?.isRefreshing = it == true
            }

            errorMessage.observe(viewLifecycleOwner) {
                context?.showToast(it)
            }
        }
    }

    override fun onDestroyView() {
        // Fragments outlive their views.
        // So, we to clean up any references to the binding class instance here.
        binding = null
        super.onDestroyView()
    }
}