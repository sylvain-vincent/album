package com.sylvainvincent.myalbums.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sylvainvincent.myalbums.core.database.dao.TrackDao
import com.sylvainvincent.myalbums.core.database.model.TrackEntity

@Database(
    entities = [
        TrackEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class MyAlbumsDatabase : RoomDatabase() {
    abstract fun trackDao() : TrackDao
}
