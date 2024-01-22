package com.moni.scoreapp.data.local.guimodels

import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.data.local.enums.RecordStatus
import java.time.ZoneId
import java.time.ZonedDateTime

data class RecordGuiModel(
    val id: String,
    val clientName: String,
    val date: ZonedDateTime,
    val status: RecordStatus
) {
    constructor(recordItem: RecordItem) : this(
        id = recordItem.id,
        clientName = "${recordItem.name} ${recordItem.lastname}",
        date = ZonedDateTime.ofInstant(recordItem.createdDate, ZoneId.systemDefault()),
        status = recordItem.status
    )
}
