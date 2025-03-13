package com.example.passtracker.app.di

import android.content.Context
import com.example.passtracker.data.constants.DataConstants
import com.example.passtracker.data.network.PassTrackerAPI
import com.example.passtracker.data.network.SessionManager
import com.example.passtracker.data.network.TokenInterceptor
import com.example.passtracker.data.network.UnsafeOkHttpClient
import com.example.passtracker.data.repository.ProfileRepositoryImpl
import com.example.passtracker.domain.repository.ProfileRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single { androidContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    single { SessionManager(androidContext()) }

    single<okhttp3.Interceptor> { TokenInterceptor(get(), get()) }

    single<OkHttpClient> {
        UnsafeOkHttpClient.getUnsafeOkHttpClient(get())
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