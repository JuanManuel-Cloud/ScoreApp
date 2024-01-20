package data.guimodels

import com.moni.scoreapp.ui.home.data.enums.RecordStatus
import java.time.ZonedDateTime

data class RecordGuiModel(val clientName: String, val date: ZonedDateTime, val status: RecordStatus)
