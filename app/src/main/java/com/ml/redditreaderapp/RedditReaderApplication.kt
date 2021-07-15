package com.ml.redditreaderapp

import android.app.Application
import com.ml.redditreaderapp.injection.repositoryModule
import com.ml.redditreaderapp.injection.viewModelModule
import com.ml.redditreaderapp.network.databaseModule
import com.ml.redditreaderapp.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class RedditReaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@RedditReaderApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }

}