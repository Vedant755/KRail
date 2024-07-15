package com.vedant.krail.ui.homeScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vedant.krail.model.AllTrainsResponse
import com.vedant.krail.model.Train
import com.vedant.krail.utils.Server
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject



@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val server: Server
) : ViewModel() {

    private val _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private val _trainNumber = MutableLiveData<List<String>>()
    val trainNumber: LiveData<List<String>> = _trainNumber

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        fetchAllTrains()
    }

    fun fetchAllTrains() {
        _isRefreshing.value = true
        val response = server.getAllTrains()
        response.enqueue(object : Callback<AllTrainsResponse> {
            override fun onResponse(
                call: Call<AllTrainsResponse>,
                response: Response<AllTrainsResponse>
            ) {
                if (response.isSuccessful) {
                    _trainNumber.value = response.body()?.trains?.keys?.toList()?: emptyList()
                    val allTrains = response.body()?.trains?.values?.toList() ?: emptyList()
                    _trains.value = allTrains
                } else {
                    Log.e("Error All Trains", response.code().toString())
                }
                _isRefreshing.value = false
            }

            override fun onFailure(call: Call<AllTrainsResponse>, t: Throwable) {
                Log.e("Error All Trains", t.message.toString())
                _isRefreshing.value = false
            }
        })
    }

    fun refreshTrains() {
        fetchAllTrains()
    }
}
