package com.vedant.krail.model

data class Location(
    val description: String,
    val distance: String,
    val name: String,
    val state: String,
    val type: String
)

data class StationResponse(
    val data: Location,
    val success: Boolean
)

