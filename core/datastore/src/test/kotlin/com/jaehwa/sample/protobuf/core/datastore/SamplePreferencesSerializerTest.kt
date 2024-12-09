package com.jaehwa.sample.protobuf.core.datastore

import androidx.datastore.core.CorruptionException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class SamplePreferencesSerializerTest {
    private val samplePreferencesSerializer = SamplePreferencesSerializer()

    @Test
    fun defaultSamplePreferences() {
        assertEquals(
            samplePreferences { },
            samplePreferencesSerializer.defaultValue
        )
    }

    @Test
    fun writingAndReadingSamplePreferences_outputsCorrectValue() = runTest {
        val expectedSamplePreferences = samplePreferences {
            id = 1
            data = "data"
        }
        val outputStream = ByteArrayOutputStream()
        expectedSamplePreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val actualSamplePreferences = samplePreferencesSerializer.readFrom(inputStream)

        assertEquals(
            expectedSamplePreferences,
            actualSamplePreferences
        )
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidSamplePreferences_throwsCorruptionException() = runTest {
        samplePreferencesSerializer.readFrom(
            ByteArrayInputStream(byteArrayOf(0))
        )
    }
}