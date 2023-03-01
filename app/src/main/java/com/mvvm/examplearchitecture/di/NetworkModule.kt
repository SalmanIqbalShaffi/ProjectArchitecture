package com.example.agentmate.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.mvvm.examplearchitecture.apiInterface.MyApiInterface
import com.mvvm.examplearchitecture.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private val baseRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    private fun getOkHttpClient(interceptor: AuthTokenInterceptor): OkHttpClient {
        val builder = OkHttpClient().newBuilder()

        //Certificate pinning here
//        builder.certificatePinner()
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor())
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Log.d("RAW_RESPONSE", message) }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideService(interceptor: AuthTokenInterceptor): MyApiInterface {
        return baseRetrofitBuilder.client(getOkHttpClient(interceptor))
            .build()
            .create(MyApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    class AuthTokenInterceptor @Inject constructor(
        private val context: Context
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request().newBuilder()
                .header("Accept", "application/json")
//                .addHeader("Authorization", authorizationHeader)
                .build()
            return chain.proceed(request)
        }
    }
}