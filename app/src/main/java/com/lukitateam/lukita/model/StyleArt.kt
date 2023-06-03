package com.lukitateam.lukita.model

import android.graphics.Paint.Style
import androidx.annotation.StringRes
import com.lukitateam.lukita.R

data class StyleArt(
    @StringRes val styleArt: Int
)

val styleArts = listOf(
    R.string.style_art_naturalism,
    R.string.style_art_surrealism,
    R.string.style_art_history,
    R.string.style_art_abstract,
    R.string.style_art_abstract,
    R.string.style_art_abstract,
    R.string.style_art_abstract,
).map { StyleArt(it) }
