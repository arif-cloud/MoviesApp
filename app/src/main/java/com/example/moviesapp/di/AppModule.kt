package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.local.MovieDao
import com.example.moviesapp.data.local.MovieDatabase
import com.example.moviesapp.data.remote.MovieApi
import com.example.moviesapp.data.repository.MovieRepositoryImplementation
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideMovieApi() : MovieApi{
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).
        client(client).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi) : MovieRepository {
        return MovieRepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context : Context) : MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao {
        return movieDatabase.dao()
    }
}