package com.nischal.newsapp.ui.newsDetail

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.nischal.newsapp.R
import com.nischal.newsapp.databinding.FragmentNewsDetailsBinding
import com.nischal.newsapp.ui.main.MainActivity
import com.nischal.newsapp.ui.newsList.models.NewsDisplayModel
import com.nischal.newsapp.utils.gone
import com.nischal.newsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {
    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var binding: FragmentNewsDetailsBinding? = null

    private val selectedNewsItem: NewsDisplayModel? by lazy {
        requireArguments().getParcelable(SELECTED_NEWS_ITEM) as NewsDisplayModel?
    }

    companion object {
        const val SELECTED_NEWS_ITEM = "selected_news_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_news_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareContent()
                true
            }
            android.R.id.home -> {
                (activity as MainActivity?)?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        (activity as MainActivity?)?.supportActionBar?.run {
            title = selectedNewsItem?.headline
            setDisplayHomeAsUpEnabled(true)
        }
        binding?.wbNewsDetails?.run {
            settings.javaScriptEnabled = false

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding?.pbWebView?.visible()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding?.pbWebView?.gone()
                }
            }
            selectedNewsItem?.url?.let { loadUrl(it) }
        }
    }

    override fun onDestroyView() {
        // Fragments outlive their views.
        // So, we to clean up any references to the binding class instance here.
        binding = null
        super.onDestroyView()
    }

    /**
     * Share the news url to other apps.
     */
    private fun shareContent() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, selectedNewsItem?.url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}