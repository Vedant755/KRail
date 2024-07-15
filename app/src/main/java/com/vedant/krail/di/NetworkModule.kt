package com.vedant.krail.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vedant.krail.model.TrainResponse
import com.vedant.krail.model.TrainResponseDeserializer
import com.vedant.krail.utils.Constants.BASE_URL
import com.vedant.krail.utils.Server
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(TrainResponse::class.java, TrainResponseDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideServer(gson: Gson): Server {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(Server::class.java)
    }
}
