package com.mobinity.mvvm_dagger_practice.di

import com.mobinity.mvvm_dagger_practice.repository.MainRepository
import com.mobinity.mvvm_dagger_practice.retrofit.BlogRetrofit
import com.mobinity.mvvm_dagger_practice.retrofit.NetworkMapper
import com.mobinity.mvvm_dagger_practice.room.BlogDao
import com.mobinity.mvvm_dagger_practice.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }

}
