package com.sylvainvincent.myalbums.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query(
        value =
        """
        SELECT * FROM track
        WHERE id = :trackId
        """,
    )
    fun getTrackEntity(trackId: String): Flow<TrackEntity>

    @Query(
        value =
        """
        SELECT * FROM track
        """,
    )
    fun getTracksEntity(): Flow<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(entities: List<TrackEntity>) : List<Long>
}