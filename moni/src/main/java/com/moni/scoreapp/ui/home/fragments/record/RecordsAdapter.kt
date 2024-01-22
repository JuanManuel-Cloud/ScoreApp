package com.moni.scoreapp.ui.home.fragments.record

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.button.MaterialButton
import com.moni.scoreapp.R
import com.moni.scoreapp.data.local.enums.RecordStatus
import com.moni.scoreapp.data.local.guimodels.RecordGuiModel
import java.time.format.DateTimeFormatter
import kotlin.math.max

class RecordsAdapter(
    val context: Context,
    var records: List<RecordGuiModel>,
    private val onDeleteListener: (String) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    inner class RecordViewHolder(itemView: View) : ViewHolder(itemView) {
        private val clientName: TextView = itemView.findViewById(R.id.card_name)
        private val date: TextView = itemView.findViewById(R.id.card_date)
        private val cardIcon: ImageView = itemView.findViewById(R.id.card_ic)
        private val cardDeleteBtn: MaterialButton = itemView.findViewById(R.id.card_delete_btn)
        private var currentRecord: RecordGuiModel? = null

        fun bind(record: RecordGuiModel) {
            currentRecord = record
            clientName.text = record.clientName
            date.text = record.date.format(DateTimeFormatter.ofPattern("dd-MM-yy"))
            when (record.status) {
                RecordStatus.APPROVE -> {
                    cardIcon.setColorFilter(ContextCompat.getColor(context, R.color.green50))
                    cardIcon.setImageResource(R.drawable.ic_approved)
                }

                RecordStatus.REJECTED -> {
                    cardIcon.setColorFilter(ContextCompat.getColor(context, R.color.error50))
                    cardIcon.setImageResource(R.drawable.ic_rejected)
                }
            }
            cardDeleteBtn.setOnClickListener { onDeleteListener(record.id) }
        }
    }

    inner class EmptyHolder(v: View) : ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            EMPTY -> EmptyHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty_record, parent, false)
            )

            else -> RecordViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_record, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int) = when {
        records.isEmpty() -> EMPTY
        else -> RECORD
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is RecordViewHolder) {
            val record = records[position]
            holder.bind(record)
        }
    }

    override fun getItemCount(): Int = max(records.size, 1)

    companion object {
        private const val EMPTY = 0
        private const val RECORD = 1
    }
}