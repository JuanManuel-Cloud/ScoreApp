package com.moni.scoreapp.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import java.lang.reflect.Type
import java.time.Instant
import java.util.Locale

class GendersDeserializer : JsonDeserializer<Genders> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Genders {
        return Genders.valueOf(json.asString.uppercase(Locale.ROOT))
    }
}

class StatusDeserializer : JsonDeserializer<RecordStatus> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): RecordStatus {
        return RecordStatus.valueOf(json.asString.uppercase(Locale.ROOT))
    }
}

class InstantDeserializer : JsonDeserializer<Instant> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Instant {
        return Instant.parse(json.asString)
    }
}