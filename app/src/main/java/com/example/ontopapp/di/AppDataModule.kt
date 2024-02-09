package com.example.ontopapp.di

import com.example.ontopapp.data.database.MovieLocalDataSourceImpl
import com.example.ontopapp.data.datasource.MoviesLocalDataSource
import com.example.ontopapp.data.datasource.MoviesRemoteDataSource
import com.example.ontopapp.data.server.MoviesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindMoviesRemoteDataSource(moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl): MoviesRemoteDataSource

    @Binds
    abstract fun bindMoviesLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MoviesLocalDataSource
}
