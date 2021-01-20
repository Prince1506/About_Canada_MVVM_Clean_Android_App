package com.mvvm_clean.about_canada.core.di

import android.content.Context
import com.mvvm_clean.about_canada.AboutCanadaApplication
import com.mvvm_clean.about_canada.BuildConfig
import com.mvvm_clean.about_canada.features.canada_facts.domain.repo.AboutCanadaRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AboutCanadaApplication) {
    var timeout = 5 * 10L
    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()


        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)

        }
        okHttpClientBuilder.readTimeout(timeout, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(timeout, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides @Singleton fun provideCanadaFactsRepository(dataSource: AboutCanadaRepository.Network): AboutCanadaRepository = dataSource
}
