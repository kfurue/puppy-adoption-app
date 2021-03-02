package com.example.puppyadoption.model

import androidx.annotation.DrawableRes

data class Item(
    val id: Int,
    val title: String,
    val subtitle: String? = null,
    @DrawableRes
    val imageId: Int
)
