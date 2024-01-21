package com.moni.scoreapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moni.scoreapp.data.local.daos.ScoreDao
import com.moni.scoreapp.data.local.daos.ScoreDatabase
import com.moni.scoreapp.data.local.enums.Genders
import com.moni.scoreapp.data.local.enums.RecordStatus
import com.moni.scoreapp.data.remote.GendersDeserializer
import com.moni.scoreapp.data.remote.MoniAPI
import com.moni.scoreapp.data.remote.MoniFirebaseAPI
import com.moni.scoreapp.data.remote.StatusDeserializer
import com.moni.scoreapp.repositories.DefaultScoreRepository
import com.moni.scoreapp.repositories.ScoreRepository
import com.moni.scoreapp.utils.Constants.BASE_FIREBASE_URL
import com.moni.scoreapp.utils.Constants.BASE_URL
import com.moni.scoreapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideScoreItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ScoreDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultScoreRepository(
        dao: ScoreDao,
        moniAPI: MoniAPI,
        firebaseAPI: MoniFirebaseAPI
    ) = DefaultScoreRepository(dao, moniAPI, firebaseAPI) as ScoreRepository

    @Singleton
    @Provides
    fun provideScoreDao(
        database: ScoreDatabase
    ) = database.scoreDao()

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Genders::class.java, GendersDeserializer())
            .registerTypeAdapter(RecordStatus::class.java, StatusDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideMoniApi(gson: Gson): MoniAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(MoniAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMoniFirebaseApi(gson: Gson): MoniFirebaseAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_FIREBASE_URL)
            .build()
            .create(MoniFirebaseAPI::class.java)
    }
}