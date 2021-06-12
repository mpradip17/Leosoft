package com.kotlintest.app.viewModel

import androidx.lifecycle.MutableLiveData
import com.kotlintest.app.appControl.AppController
import com.kotlintest.app.baseClass.BaseViewModel
import com.kotlintest.app.database.AppDatabase
import com.kotlintest.app.model.Sample
import com.kotlintest.app.network.CommonApi
import com.kotlintest.app.network.Response
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException
import java.util.*

class HomeViewModel(var commonApi: CommonApi)  : BaseViewModel() {


    fun response(): MutableLiveData<Response> {
        return response
    }

    fun apiCall(){
        commonApi.getApi(response,disable)
    }


    fun loadAllUser(): ArrayList<Sample> {
        return try {
            Observable.create<ArrayList<Sample>>(
                ObservableOnSubscribe<ArrayList<Sample>> { subscriber: ObservableEmitter<ArrayList<Sample>> ->
                    try {
                        val usersModel: ArrayList<Sample> =
                            AppDatabase.getInstance(AppController.mInstance).userDao()
                                .getBusiness()
                        if (usersModel != null) subscriber.onNext(usersModel) else subscriber.onError(
                            NullPointerException("The value is Null")
                        )
                        subscriber.onComplete()
                    } catch (e: Exception) {
                        subscriber.onError(e)
                    }
                } as ObservableOnSubscribe<ArrayList<Sample>>)
                .subscribeOn(Schedulers.computation()).firstElement().blockingGet()
        } catch (e: Exception) {
            ArrayList<Sample>()
        }
    }

}