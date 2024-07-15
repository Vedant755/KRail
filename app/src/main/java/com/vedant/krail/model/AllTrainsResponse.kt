package com.vedant.krail.model



data class AllTrainsResponse(
    val count: Int,
    val lastFetchedAt: String,
    val lastUpdateAtUpstream: String,
    val trains: Map<String, Train>,
    val success: Boolean
)

data class Train(
    val delayedTime: Time,
    val direction: String,
    val name: String,
    val station: String,
    val status: String,
    val statusTime: Time,
    val type: String
)

data class Time(
    val hours: String,
    val minutes: String
)
