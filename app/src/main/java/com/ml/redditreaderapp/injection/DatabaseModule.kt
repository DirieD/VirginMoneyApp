package com.ml.redditreaderapp.network

import android.app.Application
import androidx.room.Room
import com.ml.redditreaderapp.db.DatabaseConstants.DATABASE_NAME
import com.ml.redditreaderapp.db.PostDatabase
import com.ml.redditreaderapp.db.PostsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {

    fun provideDatabase(application: Application) : PostDatabase {
        return Room.databaseBuilder(application, PostDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
}