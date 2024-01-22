package com.moni.scoreapp.data.remote.models

import com.google.gson.annotations.SerializedName
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import java.time.Instant

data class ScoreRs(
    @SerializedName("has_error")
    val hasError: Boolean,
    val status: RecordStatus
)

data class RecordListRs(
    val documents: List<RecordRs>?
)

data class RecordRs(
    val createTime: Instant,
    val fields: Fields,
    val name: String
) {
    fun getIdFromName(): String {
        val parts = name.split("/")
        return parts.last()
    }
}

data class RecordRq(
    val fields: Fields
)

data class Fields(
    val apellido: Apellido,
    val dni: Dni,
    val email: Email,
    val genero: Genero,
    val nombre: Nombre,
    val status: Status?
)

data class Nombre(val stringValue: String)
data class Apellido(val stringValue: String)
data class Dni(val stringValue: String)
data class Email(val stringValue: String)
data class Genero(val stringValue: Genders)
data class Status(val stringValue: RecordStatus?)