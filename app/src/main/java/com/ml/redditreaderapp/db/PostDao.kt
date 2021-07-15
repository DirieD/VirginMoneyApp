package com.ml.redditreaderapp.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ml.redditreaderapp.domain.RedditPost

@Dao
interface PostsDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePosts(redditPosts: List<RedditPost>)

    @Query("SELECT * FROM RedditPost")
    fun getPosts(): PagingSource<Int, RedditPost>
}