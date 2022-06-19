package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val startTime = System.currentTimeMillis()

    val deferred1 = async {
        val result1 = networkCall(1)
        println("result1 received: $result1 after ${elapsedMillis(startTime)}ms ")
        result1
    }
    val deferred2 = async {
        val result2 = networkCall(2)
        println("result1 received: $result2 after ${elapsedMillis(startTime)}ms ")
        result2
    }

    deferred1.start()

    val resultList = listOf(deferred1.await(), deferred2.await())

    println("Result list: $resultList after ${elapsedMillis(startTime)}ms")
}

suspend fun networkCall(number: Int): String {
    delay(500)
    return "Result $number"
}

fun elapsedMillis(startTime: Long) = System.currentTimeMillis() - startTime