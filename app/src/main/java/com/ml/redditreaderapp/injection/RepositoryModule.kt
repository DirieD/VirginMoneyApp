package com.ml.redditreaderapp.injection

import com.ml.redditreaderapp.repository.RedditRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RedditRepository(get(), get()) }
}