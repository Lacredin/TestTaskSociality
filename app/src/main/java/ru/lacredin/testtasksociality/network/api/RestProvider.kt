package ru.lacredin.testtasksociality.network.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.lacredin.testtasksociality.utils.SERVER_ADDR
import javax.inject.Inject


class RestProvider @Inject constructor() {

    private var baseUrl = SERVER_ADDR
    private var instance: RestProvider? = null

    internal var restAPI: Api

    init {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(getLoggingInterceptor())

        val client = httpClientBuilder
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

        restAPI = retrofit.create(Api::class.java)
    }

    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}