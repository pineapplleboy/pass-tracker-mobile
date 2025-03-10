package com.example.passtracker.app.di

import com.example.passtracker.data.constants.DataConstants
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.data.network.UnsafeOkHttpClient
import com.example.passtracker.data.repository.ProfileRepository
import com.example.passtracker.domain.repository.ProfileRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {

        Retrofit.Builder()
            .baseUrl(DataConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
            .build()
    }


    single { get<Retrofit>().create(PassTrackerAPI::class.java) }

    single { SessionManager(get()) }

    factory<ProfileRepository>{
        ProfileRepositoryImpl(get(), get())
    }
}