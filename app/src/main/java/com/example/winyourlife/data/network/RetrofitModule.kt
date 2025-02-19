package com.example.winyourlife.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{
    private const val BASE_URL ="http://192.168.76.35:8080/"

    @Provides
    fun okHttpClient(jwtManager: JwtManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ErrorInterceptor())
            .addInterceptor { chain ->
                if (jwtManager.getJwt() == null){
                    return@addInterceptor chain.proceed(chain.request())
                }
                if (chain.request().url().toString().contains("login") || chain.request().url().toString().contains("register")){
                    return@addInterceptor chain.proceed(chain.request())
                }
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", jwtManager.getJwt())
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun getInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
