package com.app.shourtcuttask.framework.datasource.local

import androidx.room.*
import com.app.shourtcuttask.framework.datasource.local.entity.ComicDbTable

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comicDBEntity: ComicDbTable)

    @Transaction
    @Query("SELECT * FROM ComicDbTable")
    suspend fun getComicByComicID(): List<ComicDbTable>
}