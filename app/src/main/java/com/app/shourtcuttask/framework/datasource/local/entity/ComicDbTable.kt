package com.app.shourtcuttask.framework.datasource.local.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComicDbTable(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var img: Bitmap? = null,
    var title: String = "",
    var description: String = ""
)