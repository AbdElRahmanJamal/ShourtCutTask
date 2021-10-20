package com.app.shourtcuttask.framework.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

@Database(
    entities = [ComicDbTable::class],
    version = 1
)
@TypeConverters(BitmapConverter::class)
abstract class ComicDatabase : RoomDatabase() {

    abstract val comicDao: ComicDao
}