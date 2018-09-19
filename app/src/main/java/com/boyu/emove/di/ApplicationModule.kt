package com.boyu.emove.di

import android.content.Intent
import android.util.Log
import com.boyu.emove.AndroidApplication
import com.boyu.emove.BuildConfig
import com.boyu.emove.Login.ui.LoginActivity
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.BaseResponse2
import com.boyu.emove.utils.SharedPreferencesUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.Module
import dagger.Provides
import okhttp3.*
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
            val requestInterceptor = RequestInterceptor(application)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            okHttpClientBuilder.addInterceptor(requestInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    private class RequestInterceptor(val application: AndroidApplication): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var originalRequest = chain.request()

            var token by SharedPreferencesUtil(this@RequestInterceptor.application,"token","")
            var uid by SharedPreferencesUtil(this@RequestInterceptor.application,"uid","")

            var url: HttpUrl? = null
            if(token.length > 0) {
                 url = originalRequest.url().newBuilder()
                        .addQueryParameter("eappid","8102")
                        .addQueryParameter("banjia_type", "1")
                         .addQueryParameter("uid",uid)
                         .addQueryParameter("token",token)
                        .build()
            }else {
                url = originalRequest.url().newBuilder()
                        .addQueryParameter("eappid","8102")
                        .addQueryParameter("banjia_type", "1")
                        .build()
            }
            originalRequest = originalRequest.newBuilder().url(url).build()

            // response
            val response = chain.proceed(originalRequest)

            var responseString = response.body()?.string()
            val mediaType = response.body()?.contentType()

            var baseResponse = Gson().fromJson(responseString, BaseResponse2::class.java)
            if (baseResponse.code == 6004){
                token = ""
                uid = ""
                var intent = Intent(application, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(intent)
            }

            val responseBody = ResponseBody.create(mediaType, responseString)

            return response.newBuilder().body(responseBody).build()
        }

    }
}