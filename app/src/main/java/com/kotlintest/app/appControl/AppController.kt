package com.kotlintest.app.appControl

import android.app.Application
import android.util.Log
import com.kotlintest.app.BuildConfig
import com.kotlintest.app.utility.di.apiModule
import com.kotlintest.app.utility.di.netModule
import com.kotlintest.app.utility.di.repositoryModule
import com.kotlintest.app.utility.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree


class AppController : Application() {
    val DatabaseVersion = 1
    val DatabaseName: String = "_Room" + "_db"
    companion object AppController {
        var mInstance: com.kotlintest.app.appControl.AppController? = null

    }

    @Synchronized
    fun getInstance(): com.kotlintest.app.appControl.AppController? {
        return AppController.mInstance
    }
    fun setmInstance(mInstance: com.kotlintest.app.appControl.AppController) {
        AppController.mInstance = mInstance
    }

    override fun onCreate() {
        super.onCreate()
        //logging module
        setmInstance(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        startKoin {
            androidContext(this@AppController)
            androidLogger(Level.DEBUG)
            modules(listOf( repositoryModule, netModule, apiModule, viewModelModule))
        }
    }



    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
        }
    }
}