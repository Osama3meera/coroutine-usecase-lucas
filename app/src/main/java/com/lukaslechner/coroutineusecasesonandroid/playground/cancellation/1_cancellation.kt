package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(10) {
            println("operation number $it")
            try {
                delay(100)
            } catch (e: CancellationException) {
                print("CancellationException was thrown!")
                throw CancellationException()
            }
        }
    }
    delay(250)
    println("Cancelling Coroutine")
    job.cancel()
}