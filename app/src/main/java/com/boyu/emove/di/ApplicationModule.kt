package com.boyu.emove.di

import android.content.Intent
import com.boyu.emove.AndroidApplication
import com.boyu.emove.BuildConfig
import com.boyu.emove.login.ui.LoginActivity
import com.boyu.emove.api.BaseResponse2
import com.boyu.emove.utils.SharedPreferencesUtil
import com.google.gson.Gson
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
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val requestInterceptor = RequestInterceptor(application)
        okHttpClientBuilder.addInterceptor(requestInterceptor)

        return okHttpClientBuilder.build()
    }

    private class RequestInterceptor(val application: AndroidApplication): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var originalRequest = chain.request()

            var token by SharedPreferencesUtil(this@RequestInterceptor.application,"token","")
            var uid by SharedPreferencesUtil(this@RequestInterceptor.application,"uid","")
            var banjia_type by SharedPreferencesUtil(this@RequestInterceptor.application,"banjia_type","1")

            var url: HttpUrl? = null
            if(token.length > 0) {
                 url = originalRequest.url().newBuilder()
                        .addQueryParameter("eappid","8102")
                        .addQueryParameter("banjia_type", banjia_type)
                         .addQueryParameter("uid",uid)
                         .addQueryParameter("token",token)
                        .build()
            }else {
                url = originalRequest.url().newBuilder()
                        .addQueryParameter("eappid","8102")
                        .addQueryParameter("banjia_type", banjia_type)
                        .build()
            }
            originalRequest = originalRequest.newBuilder().url(url).build()

            // response
            val response = chain.proceed(originalRequest)

            var responseString = response.body()?.string()
            val mediaType = response.body()?.contentType()

            try {
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

            }catch (e: Exception){
                val responseBody = ResponseBody.create(mediaType, "{code: -1, msg: \"未知错误\", result: null}")

                return response.newBuilder().body(responseBody).build()
            }

        }

    }
}