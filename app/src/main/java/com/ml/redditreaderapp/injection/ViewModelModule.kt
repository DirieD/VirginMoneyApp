package com.ml.redditreaderapp.injection

import com.ml.redditreaderapp.repository.RedditRepository
import com.ml.redditreaderapp.ui.viewmodel.RedditViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { RedditViewModel(get()) }
}