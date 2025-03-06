package com.example.passtracker.app.di

import com.example.passtracker.data.constants.DataConstants
import com.example.passtracker.data.network.AuthInterceptor
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.data.repository.ProfileRepository
import com.example.passtracker.domain.repository.ProfileRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val dataModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(get()))
            .build()

        Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    single { get<Retrofit>().create(PassTrackerAPI::class.java) }

    single { SessionManager(get()) }

    factory<ProfileRepository>{
        ProfileRepositoryImpl(get(), get())
    }
}