package com.jaehwa.sample.protobuf.core.data.repository

import com.jaehwa.sample.protobuf.core.datastore.SamplePreferencesDataSource
import com.jaehwa.sample.protobuf.core.model.data.SampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultSampleDataRepository @Inject constructor(
    private val samplePreferencesDataSource: SamplePreferencesDataSource,
) : SampleDataRepository {

    override val sampleData: Flow<SampleData> =
        samplePreferencesDataSource.sampleData.map {
            it.takeIf { it.data.isNotEmpty() } ?: it.copy(data = "0")
        }

    override suspend fun setData(data: String) {
        samplePreferencesDataSource.updateSampleData(data)
    }
}