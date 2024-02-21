package com.china.psychy.android.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface CoreDispatcher {
    fun main(): CoroutineContext
    fun io(): CoroutineContext
    fun default(): CoroutineContext
    fun unconfined(): CoroutineDispatcher
}