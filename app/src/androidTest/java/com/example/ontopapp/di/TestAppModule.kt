package com.example.ontopapp.di

import android.app.Application
import androidx.room.Room
import com.example.ontopapp.apptestshared.FakeMovieRemoteService
import com.example.ontopapp.apptestshared.buildRemoteMovies
import com.example.ontopapp.data.database.MovieDao
import com.example.ontopapp.data.database.MovieDatabase
import com.example.ontopapp.data.server.MovieRemoteService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppLocalModule::class, AppRemoteModule::class],
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        MovieDatabase::class.java,
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()

    @Provides
    @Reusable
    fun provideMovieRemoteService(): MovieRemoteService =
        FakeMovieRemoteService(buildRemoteMovies(1, 2, 3, 4, 5, 6))
}
