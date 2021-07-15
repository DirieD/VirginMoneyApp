package com.ml.redditreaderapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ml.redditreaderapp.domain.RedditPost
import com.ml.redditreaderapp.repository.RedditRepository
import kotlinx.coroutines.flow.Flow

class RedditViewModel(
    private val redditRepository: RedditRepository
) : ViewModel() {

    fun fetchPosts() : Flow<PagingData<RedditPost>> {
        return redditRepository.fetchPosts().cachedIn(viewModelScope)
    }
}