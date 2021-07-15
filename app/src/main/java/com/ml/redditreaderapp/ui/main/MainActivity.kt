package com.ml.redditreaderapp.ui.main

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import com.ml.redditreaderapp.R
import com.ml.redditreaderapp.databinding.ActivityMainBinding
import com.ml.redditreaderapp.ui.main.adapter.PostAdapter
import com.ml.redditreaderapp.ui.viewmodel.RedditViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val redditViewModel by viewModel<RedditViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupFlowCollector()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter { navigateToPost(it.url) }
        binding.postsRecyclerview.adapter = postAdapter
    }

    private fun setupFlowCollector() {
        lifecycleScope.launch {
            redditViewModel.fetchPosts().collectLatest {
                postAdapter.submitData(it)
            }
        }
    }

    private fun navigateToPost(url: String) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }
}