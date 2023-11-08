/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vince.mercadolibre.utils

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.junit.Assert

/**
 * Gets the value of a [LiveData] or waits for it to have one, with a timeout.
 *
 * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
 * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {},
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        afterObserve.invoke()
    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
inline fun <reified T> LiveData<T>.observeForTesting(
    observer: Observer<T>,
    expectedCalls: Int = 3,
    block: () -> Unit
) {
    val timeout = 5L
    var observerCalls = 0

    val latch = CountDownLatch(expectedCalls)
    val observerCounter = Observer<T> { o ->
        observerCalls++
        observer.onChanged(o)
        latch.countDown()
    }

    this.observeForever(observerCounter)

    if (!latch.await(timeout, TimeUnit.SECONDS)) {
        removeObserver(observerCounter)
        removeObserver(observer)
        Assert.assertEquals(
            "Observer wasn't called the expected number of times. Timeout: $timeout ${TimeUnit.SECONDS.name}",
            expectedCalls,
            observerCalls,
        )
        return
    }

    block()
    removeObserver(observerCounter)
    removeObserver(observer)

    Assert.assertEquals(
        "Observer wasn't called the expected number of times",
        expectedCalls,
        observerCalls
    )
}
