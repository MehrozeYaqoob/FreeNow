package com.freenow.task2.di

import com.freenow.task2.viewmodel.MapViewModelFactory
import org.koin.dsl.module

val task2Module = module {

    single {
        MapViewModelFactory()
    }
}


