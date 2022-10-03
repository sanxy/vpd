package com.sanxynet.vpd


import android.app.Application
import androidx.room.Room
import com.sanxynet.vpd.db.UserDatabase
import timber.log.Timber


class Vpd : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize timber in debug mode
        Timber.plant(Timber.DebugTree())

    }
}