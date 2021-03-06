package com.app.shourtcuttask.framework.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class ComicDto(
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    val num: Int,
    @SerializedName("safe_title")
    val safeTitle: String,
    val title: String,
    val transcript: String,
    val year: String
)