package com.example.firstapp.data

data class LibraryItem(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val isArtist: Boolean = false
)
