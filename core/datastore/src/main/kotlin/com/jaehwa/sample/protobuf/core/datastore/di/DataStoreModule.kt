package com.jaehwa.sample.protobuf.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.jaehwa.sample.protobuf.core.AppDispatchers
import com.jaehwa.sample.protobuf.core.Dispatcher
import com.jaehwa.sample.protobuf.core.datastore.SamplePreferences
import com.jaehwa.sample.protobuf.core.datastore.SamplePreferencesSerializer
import com.jaehwa.sample.protobuf.core.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    internal fun providesSamplePreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        samplePreferencesSerializer: SamplePreferencesSerializer,
    ): DataStore<SamplePreferences> =
        DataStoreFactory.create(
            serializer = samplePreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile(SAMPLE_PREFS_FILE_NAME)
        }

    private const val SAMPLE_PREFS_FILE_NAME = "sample_preferences.pb"
}
