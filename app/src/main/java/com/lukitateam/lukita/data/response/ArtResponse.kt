package com.lukitateam.lukita.data.response

import com.google.gson.annotations.SerializedName

data class ArtResponse(

	@field:SerializedName("Explanation")
	val explanation: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Related Image")
	val relatedImage: List<String?>? = null
)
