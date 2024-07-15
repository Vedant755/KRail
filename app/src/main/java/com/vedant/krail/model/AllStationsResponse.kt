package com.vedant.krail.model

data class Station(
    val description: String,
    val distance: String,
    val name: String,
    val state: String,
    val type: String
)

data class AllStationsResponse(
    val count: Int,
    val stations: List<Station>
)
