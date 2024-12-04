package com.sylvainvincent.myalbums.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sylvainvincent.myalbums.core.database.model.TrackEntity

@Dao
interface TrackDao {

    @Query(
        value =
        """
        SELECT * FROM track
        """,
    )
    fun getTracksEntity(): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(entities: List<TrackEntity>) : List<Long>
}