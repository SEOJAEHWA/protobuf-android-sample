package com.jaehwa.sample.protobuf.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class SamplePreferencesSerializer @Inject constructor() : Serializer<SamplePreferences> {
    override val defaultValue: SamplePreferences = SamplePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SamplePreferences =
        runCatching {
            SamplePreferences.parseFrom(input)
        }.onFailure { exception ->
            throw CorruptionException("Cannot read proto.", exception)
        }.getOrThrow()


    override suspend fun writeTo(t: SamplePreferences, output: OutputStream) {
        t.writeTo(output)
    }
}