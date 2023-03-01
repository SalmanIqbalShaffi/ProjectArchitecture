package com.example.agentmate.di

import com.mvvm.examplearchitecture.repository.MyRepository
import com.mvvm.examplearchitecture.repository.MyRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun agentRepository(agentRepositoryImpl: MyRepositoryImp): MyRepository
}