package com.jaehwa.sample.protobuf.core.data.repository

import com.jaehwa.sample.protobuf.core.model.data.SampleData
import kotlinx.coroutines.flow.Flow

interface SampleDataRepository {

    val sampleData: Flow<SampleData>

    suspend fun setData(data: String)
}