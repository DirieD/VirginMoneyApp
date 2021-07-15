package com.ml.redditreaderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ml.redditreaderapp.domain.RedditKeys

@Dao
interface KeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveRedditKeys(redditKey: RedditKeys)

    @Query("SELECT * FROM RedditKeys ORDER BY id DESC")
    suspend fun getRedditKeys(): List<RedditKeys>

}