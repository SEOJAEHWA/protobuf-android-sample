package com.jaehwa.sample.protobuf.core.data.di

import com.jaehwa.sample.protobuf.core.data.repository.DefaultSampleDataRepository
import com.jaehwa.sample.protobuf.core.data.repository.SampleDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsSampleDataSource(
        sampleDataRepository: DefaultSampleDataRepository,
    ): SampleDataRepository
}