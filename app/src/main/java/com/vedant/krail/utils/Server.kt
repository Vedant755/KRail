package com.vedant.krail.utils

import com.vedant.krail.model.AllStationsResponse
import com.vedant.krail.model.AllTrainsResponse
import com.vedant.krail.model.StationResponse
import com.vedant.krail.model.TrainResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Server {

    @GET("fetchTrains")
    fun getAllTrains():retrofit2.Call<AllTrainsResponse>

    @GET("fetchTrain/{trainNumber}")
    fun fetchTrain(@Path("trainNumber") trainNumber: String): retrofit2.Call<TrainResponse>

    @GET("fetchStations")
    fun fetchStations(): retrofit2.Call<AllStationsResponse>

    @GET("fetchStation/{stationName}")
    fun fetchStation(@Path("stationName") stationName: String): retrofit2.Call<StationResponse>


}