package com.freenow.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.freenow.task1.di.task1Module
import com.freenow.task2.di.task2Module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VehicleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startKoin {
            androidContext(this@VehicleApplication)
            modules(listOf(task1Module, task2Module))
        }
    }
}
