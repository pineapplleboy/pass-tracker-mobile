package com.example.passtracker.app.di

import android.content.Context
import com.example.passtracker.data.constants.DataConstants
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.data.network.UnsafeOkHttpClient
import com.example.passtracker.data.repository.ProfileRepositoryImpl
import com.example.passtracker.domain.repository.ProfileRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single { androidContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    single { SessionManager(androidContext()) }

    single(named("basicOkHttpClient")) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single(named("basicRetrofit")) {
        Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(named("basicOkHttpClient")))
            .build()
    }

    single(named("tokenApi")) {
        get<Retrofit>(named("basicRetrofit")).create(PassTrackerAPI::class.java)
    }

    single<OkHttpClient> {
        UnsafeOkHttpClient.getUnsafeOkHttpClient()
    }

    single {
        Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(PassTrackerAPI::class.java)
    }

    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
}