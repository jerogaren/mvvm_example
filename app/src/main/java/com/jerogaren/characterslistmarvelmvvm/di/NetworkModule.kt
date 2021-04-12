package com.jerogaren.characterslistmarvelmvvm.di

import com.jerogaren.characterslistmarvelmvvm.api.Constants.API_KEY
import com.jerogaren.characterslistmarvelmvvm.api.Constants.BASE_URL
import com.jerogaren.characterslistmarvelmvvm.api.Constants.PRIVATE_KEY
import com.jerogaren.characterslistmarvelmvvm.util.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(logging)
        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", "$ts$PRIVATE_KEY$API_KEY".md5())
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }


        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        provideRetrofit(get(), BASE_URL)
    }
}