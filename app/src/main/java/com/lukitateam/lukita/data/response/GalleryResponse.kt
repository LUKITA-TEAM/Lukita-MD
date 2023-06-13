package com.lukitateam.lukita.data.response

import com.google.gson.annotations.SerializedName

data class GalleryResponse(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String,
)
