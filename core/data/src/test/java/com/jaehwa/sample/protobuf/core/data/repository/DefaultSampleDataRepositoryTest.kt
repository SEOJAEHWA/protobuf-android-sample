package com.jaehwa.sample.protobuf.core.data.repository

import com.jaehwa.sample.protobuf.core.datastore.SamplePreferences
import com.jaehwa.sample.protobuf.core.datastore.SamplePreferencesDataSource
import com.jaehwa.sample.protobuf.core.datastore.test.InMemoryDataStore
import com.jaehwa.sample.protobuf.core.model.data.SampleData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultSampleDataRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: DefaultSampleDataRepository

    private lateinit var samplePreferencesDataSource: SamplePreferencesDataSource

    @Before
    fun setup() {
        samplePreferencesDataSource = SamplePreferencesDataSource(
            InMemoryDataStore(SamplePreferences.getDefaultInstance())
        )

        subject = DefaultSampleDataRepository(
            samplePreferencesDataSource = samplePreferencesDataSource,
        )
    }

    @Test
    fun defaultSampleDataRepository_default_sample_data_is_correct() =
        testScope.runTest {
            assertEquals(
                SampleData(id = 0, data = "0"),
                subject.sampleData.first()
            )
        }

    @Test
    fun defaultSampleDataRepository_set_data_delegates_to_sample_prefs() =
        testScope.runTest {
            subject.setData(1.toString())

            assertEquals(
                samplePreferencesDataSource.sampleData.map { it.data }.first(),
                subject.sampleData.map { it.data }.first()
            )
        }
}