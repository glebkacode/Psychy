package com.china.psychy.android.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.tatarka.inject.annotations.Inject
import kotlin.coroutines.CoroutineContext

@Inject
class CoreDispatcherImpl : CoreDispatcher {
    override fun main(): CoroutineContext = Dispatchers.Main

    override fun io(): CoroutineContext = Dispatchers.IO

    override fun default(): CoroutineContext = Dispatchers.Default
    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}