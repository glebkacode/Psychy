package com.china.psychy.android.core.dispatchers

import kotlin.coroutines.CoroutineContext

interface CoreDispatcher {
    fun main(): CoroutineContext
    fun io(): CoroutineContext
    fun default(): CoroutineContext
}