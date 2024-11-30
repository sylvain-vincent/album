package com.sylvainvincent.myalbums.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
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

    @Upsert
    suspend fun upsertTracks(entities: List<TrackEntity>)
}