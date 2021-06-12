package com.kotlintest.app.network

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kotlintest.app.appControl.AppController
import com.kotlintest.app.database.AppDatabase
import com.kotlintest.app.database.AppExecutors
import com.kotlintest.app.model.Sample
import com.kotlintest.app.utility.SharedHelper
import com.kotlintest.app.utility.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable

class  CommonApi constructor(
    var application: Application,
    val sharedHelper: SharedHelper,
    val api: ApiInterface,
    val schedulersFacade: SchedulersFacade
) {
    fun getApi(
        response: MutableLiveData<Response>,
        disable: CompositeDisposable
    ) {


        disable.add(api.getUserss()
            .doOnSubscribe({ response.postValue(Response.loading()); })
            .map(this::copyOrUpdateUsers)
            .compose(schedulersFacade.applyAsync())
            .doFinally { response.value = Response.dismiss() }
            .subscribe({
                response.value = Response.success(it!!)
            }, {
                response.value = Response.error(it)
                response.value = Response.dismiss()

            })
        )
    }
    private fun copyOrUpdateUsers(mListContacts: List<Sample>): List<Sample>? {
        for (usersModel in mListContacts) {
           insertUser(usersModel)
        }
        return mListContacts
    }
    fun insertUser(usersModel: Sample?) {
        AppExecutors.getInstance().diskIO().execute({
            try {
                AppDatabase.getInstance(AppController.mInstance).userDao()
                    .insertAll(usersModel)
            } catch (e: Exception) {

            }
        })
    }
}