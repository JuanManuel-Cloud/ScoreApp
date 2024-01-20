package com.moni.scoreapp.data.local.guimodels

import com.moni.scoreapp.data.local.enums.RecordStatus
import java.time.ZonedDateTime

data class RecordGuiModel(val clientName: String, val date: ZonedDateTime, val status: RecordStatus)
