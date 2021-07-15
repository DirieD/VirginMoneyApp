package com.ml.redditreaderapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ml.redditreaderapp.db.DatabaseConstants.DATABASE_VERSION
import com.ml.redditreaderapp.db.DatabaseConstants.EXPORT_SCHEMA
import com.ml.redditreaderapp.domain.RedditKeys
import com.ml.redditreaderapp.domain.RedditPost

@Database(entities = [RedditPost::class, RedditKeys::class], version = DATABASE_VERSION, exportSchema = EXPORT_SCHEMA)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao
    abstract fun keysDao(): KeysDao
}