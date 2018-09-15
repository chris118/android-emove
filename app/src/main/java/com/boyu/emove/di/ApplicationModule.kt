package com.boyu.emove.di

import com.boyu.emove.AndroidApplication
import com.boyu.emove.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by chrisw on 2018/8/31.
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides @Singleton fun provideApplicationContext(): AndroidApplication = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit
                .Builder()
                .baseUrl("http://api.ebanjia.cn/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            val requestInterceptor = RequestInterceptor()
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            okHttpClientBuilder.addInterceptor(requestInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    private class RequestInterceptor(): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var originalRequest = chain.request()
            val url = originalRequest.url().newBuilder().addQueryParameter("eappid","8102").build()
            originalRequest = originalRequest.newBuilder().url(url).build()
            return chain.proceed(originalRequest)
        }

    }
}