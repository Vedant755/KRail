package com.vedant.krail.model

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class TrainResponse(
    @SerializedName("lastFetchedAt")
    val lastFetchedAt: String,

    @SerializedName("lastUpdateAtUpstream")
    val lastUpdateAtUpstream: String,

    @SerializedName("success")
    val success: Boolean,

    val trainDetails: Map<String, Train>
)



class TrainResponseDeserializer : JsonDeserializer<TrainResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): TrainResponse {
        val jsonObject = json?.asJsonObject!!
        val lastFetchedAt = jsonObject.get("lastFetchedAt").asString
        val lastUpdateAtUpstream = jsonObject.get("lastUpdateAtUpstream").asString
        val success = jsonObject.get("success").asBoolean
        val trainDetails = mutableMapOf<String, Train>()

        jsonObject.entrySet().forEach { entry ->
            if (entry.key != "lastFetchedAt" && entry.key != "lastUpdateAtUpstream" && entry.key != "success") {
                trainDetails[entry.key] = context!!.deserialize(entry.value, Train::class.java)
            }
        }

        return TrainResponse(
            lastFetchedAt = lastFetchedAt,
            lastUpdateAtUpstream = lastUpdateAtUpstream,
            success = success,
            trainDetails = trainDetails
        )
    }
}
