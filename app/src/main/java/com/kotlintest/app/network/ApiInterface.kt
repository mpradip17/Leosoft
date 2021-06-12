package com.kotlintest.app.network

import com.kotlintest.app.model.Sample
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("users/")
    fun getUserss( ): Observable<List<Sample>>

}