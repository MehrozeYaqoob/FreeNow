package com.freenow.task1.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
/**
* Courtesy Delivery Hero
* */
interface AppDispatchers {
    val main: MainCoroutineDispatcher
    val computation: CoroutineDispatcher
    val io: CoroutineDispatcher

    companion object Default : AppDispatchers {

        @get:JvmSynthetic // hide getter from java
        @set:JvmSynthetic // hide setter from java
        internal var override: AppDispatchers? = null
        private val delegate get() = override ?: DefaultAppDispatchers

        override val main get() = delegate.main
        override val computation get() = delegate.computation
        override val io get() = delegate.io
    }
}

private object DefaultAppDispatchers : AppDispatchers {
    override val main get() = Dispatchers.Main
    override val computation get() = Dispatchers.Default
    override val io get() = Dispatchers.IO
}
