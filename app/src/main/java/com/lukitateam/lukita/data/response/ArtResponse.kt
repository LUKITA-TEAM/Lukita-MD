package com.lukitateam.lukita.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtResponse(

	@field:SerializedName("Explanation")
	val explanation: String,

	@field:SerializedName("Prediction")
	val prediction: String,

	@field:SerializedName("Related Image")
	val relatedImage: List<String>,
) : Parcelable
