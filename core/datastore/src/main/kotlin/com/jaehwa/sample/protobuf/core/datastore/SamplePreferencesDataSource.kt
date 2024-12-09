package com.jaehwa.sample.protobuf.core.datastore

import androidx.datastore.core.DataStore
import com.jaehwa.sample.protobuf.core.model.data.SampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SamplePreferencesDataSource @Inject constructor(
    private val samplePreferences: DataStore<SamplePreferences>
) {

    val sampleData: Flow<SampleData> = samplePreferences.data
        .map {
            SampleData(
                id = it.id,
                data = it.data,
            )
        }

    suspend fun updateSampleData(data: String) {
        samplePreferences.updateData {
            it.copy {
                this.data = data
            }
        }
    }
}