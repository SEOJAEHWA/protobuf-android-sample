package com.jaehwa.sample.protobuf.core.datastore.test.di

import androidx.datastore.core.DataStore
import com.jaehwa.sample.protobuf.core.datastore.SamplePreferences
import com.jaehwa.sample.protobuf.core.datastore.SamplePreferencesSerializer
import com.jaehwa.sample.protobuf.core.datastore.di.DataStoreModule
import com.jaehwa.sample.protobuf.core.datastore.test.InMemoryDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataStoreModule::class],
)
internal object TestDataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        samplePreferencesSerializer: SamplePreferencesSerializer,
    ): DataStore<SamplePreferences> = InMemoryDataStore(samplePreferencesSerializer.defaultValue)
}
