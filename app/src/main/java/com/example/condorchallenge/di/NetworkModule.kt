package com.example.condorchallenge.di

import com.example.condorchallenge.repo.ApiClient
import com.example.condorchallenge.repo.TeamRepoImpl
import com.example.condorchallenge.repo.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit) : ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


}

@Module
@InstallIn(SingletonComponent::class)
interface repositoryModule{
    @Binds
    fun provideRepositoryImpl(repositoryImpl: TeamRepoImpl) : TeamRepository
}