package com.freenow.task1.common

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlin.coroutines.ContinuationInterceptor

/**
 * Courtesy Delivery Hero
 * */
inline fun CoroutineScope.exceptionHandler(
    crossinline block: suspend (Throwable) -> Unit
) = CoroutineExceptionHandler { _, throwable ->
    val mainDispatcher = (coroutineContext[ContinuationInterceptor] as? MainCoroutineDispatcher ?: AppDispatchers.main)
    launch(mainDispatcher.immediate) { block(throwable) }
}
