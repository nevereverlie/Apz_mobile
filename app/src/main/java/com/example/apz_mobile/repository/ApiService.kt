package com.example.apz_mobile.repository

import com.example.apz_mobile.models.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun loginUser(@Body requestBody: LoginRequest): Single<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    fun registerUser(@Body requestBody: RegisterRequest): Completable

    @GET("users")
    fun getUsers(): Single<List<User>>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ): Single<User>

    @GET("medications/{userId}")
    fun getMedications(
        @Path("userId") userId: Int
    ): Single<List<Medication>>

    @POST("medications/add")
    fun addMedication(@Body medication: Medication): Completable

    @DELETE("medications/{medicationId}")
    fun deleteMedication(
            @Path("medicationId") medicationId: Int
    ): Completable


    companion object {
        private const val BASE_URL = "https://apz.azurewebsites.net/"

        var token: String = ""
            set(value) { field = "Bearer " + value }

        fun getWebService(): ApiService {
            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            clientBuilder.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}