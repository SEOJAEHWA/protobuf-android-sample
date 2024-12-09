package com.jaehwa.sample.protobuf.core.datastore

import com.jaehwa.sample.protobuf.core.datastore.test.InMemoryDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SamplePreferencesDataSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: SamplePreferencesDataSource

    @Before
    fun setup() {
        subject = SamplePreferencesDataSource(
            InMemoryDataStore(SamplePreferences.getDefaultInstance())
        )
    }

    @Test
    fun idIsZeroByDefault() = testScope.runTest {
        Assert.assertEquals(subject.sampleData.first().id, 0)
    }

    @Test
    fun dataIsEmptyByDefault() = testScope.runTest {
        Assert.assertEquals(subject.sampleData.first().data, "")
    }

    @Test
    fun dataIsDataWhenSet() = testScope.runTest {
        subject.updateSampleData("data")
        Assert.assertEquals(subject.sampleData.first().data, "data")
    }
}