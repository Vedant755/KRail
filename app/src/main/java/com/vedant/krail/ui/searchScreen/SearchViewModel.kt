package com.vedant.krail.ui.searchScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vedant.krail.model.TrainResponse
import com.vedant.krail.utils.Server
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val server: Server
) : ViewModel() {
    private val _trainResponse = MutableLiveData<Result<TrainResponse>>()
    val trainResponse: LiveData<Result<TrainResponse>> get() = _trainResponse

    fun fetchTrain(trainNumber: String): LiveData<Result<TrainResponse>> {
        Log.d("SearchViewModel", "Fetching train data for train number: $trainNumber")
        val result = MutableLiveData<Result<TrainResponse>>()
        server.fetchTrain(trainNumber).enqueue(object : Callback<TrainResponse> {
            override fun onResponse(call: Call<TrainResponse>, response: Response<TrainResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("SearchViewModel", "Response: ${it}")
                        Log.d("SearchViewModel", "Raw Response: ${response.raw()}")
                        _trainResponse.postValue(Result.success(it))
                    }
                } else {
                    Log.e("SearchViewModel", "Error: ${response.errorBody()?.string()}")
                    _trainResponse.postValue(Result.failure(Exception("Error fetching train data")))
                }
            }

            override fun onFailure(call: Call<TrainResponse>, t: Throwable) {
                Log.e("SearchViewModel", "Failure: ${t.message}")
                result.postValue(Result.failure(t))
            }
        })
        return result
    }
}
