package com.moni.scoreapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.data.remote.models.RecordRq
import com.moni.scoreapp.data.remote.models.ScoreRs
import com.moni.scoreapp.repositories.ScoreRepository
import com.moni.scoreapp.utils.Event
import com.moni.scoreapp.utils.Resource
import com.moni.scoreapp.utils.ResStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    var recordRq: RecordRq? = null

    private val allRecords: LiveData<List<RecordItem>> = scoreRepository.getAllRecords()
    private var filteredRecords: LiveData<List<RecordItem>> = MutableLiveData()
    private val _records: MediatorLiveData<List<RecordItem>> = MediatorLiveData()
    val records: MediatorLiveData<List<RecordItem>>
        get() = _records

    private val _scoreStatus = MutableLiveData<Event<Resource<ScoreRs>>>()
    val scoreStatus: LiveData<Event<Resource<ScoreRs>>> = _scoreStatus

    private val _recordStatus = MutableLiveData<Event<Resource<Unit>>>()
    val recordStatus: LiveData<Event<Resource<Unit>>> = _recordStatus

    init {
        _records.addSource(allRecords) {
            _records.value = it
        }
    }

    fun getScore(dni: String) = viewModelScope.launch {
        _scoreStatus.postValue(Event(Resource.loading(null)))
        val scoreRs = scoreRepository.getScore(dni)

        when (scoreRs.status) {
            ResStatus.SUCCESS -> {
                scoreRs.data?.let {
                    _scoreStatus.postValue(Event(Resource.success(it)))
                }
            }

            ResStatus.ERROR -> {
                _scoreStatus.postValue(Event(Resource.error(scoreRs.message!!, null)))
                return@launch
            }

            else -> {
                _scoreStatus.postValue(Event(Resource.loading(null)))
            }
        }
    }

    fun findRecords(query: String, all: Boolean = false) {
        val newSource = scoreRepository.findRecords(query, all)
        _records.removeSource(filteredRecords)
        _records.addSource(newSource) {
            _records.value = it
        }
        filteredRecords = newSource
    }

    fun getRecordsFirebase() = viewModelScope.launch {
        _recordStatus.postValue(Event(Resource.loading(null)))
        resHandler(scoreRepository.getRecordsFirebase())
    }

    fun createRecordFirebase(recordRq: RecordRq) = viewModelScope.launch {
        _recordStatus.postValue(Event(Resource.loading(null)))
        resHandler(scoreRepository.createRecordFirebase(recordRq))
    }

    fun deleteRecordFirebase(id: String) = viewModelScope.launch {
        _recordStatus.postValue(Event(Resource.loading(null)))
        resHandler(scoreRepository.deleteRecordFirebase(id))
    }

    fun clearRecordRq() {
        recordRq = null
    }

    private fun <T> resHandler(recordRes: Resource<T>) {
        when (recordRes.status) {
            ResStatus.SUCCESS ->
                _recordStatus.postValue(Event(Resource.success(null)))

            ResStatus.ERROR -> {
                _recordStatus.postValue(Event(Resource.error(recordRes.message!!, null)))
                return
            }

            else -> _recordStatus.postValue(Event(Resource.loading(null)))
        }
    }
}