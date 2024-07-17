package idp.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * "Обмін даними між coroutines
 *     - створити програму з двома coroutines: одна генерує дані, а інша їх споживає;
 *     - використати канали (`channel`) для безпечного обміну даними між coroutines;
 *     - одна coroutine надсилає дані в канал, а інша їх отримує та обробляє."
 */
@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val channel = Channel<Int> { println("Data: # $it # not delivered") }.apply {
        invokeOnClose {
            println("Channel closed: ${it?.message}")
        }
    }

    generateData(channel)
    readData(channel)

    CoroutineScope(Dispatchers.IO).launch {
        delay(11_000L)
        channel.close(Exception("Timeout"))
    }

    // wait close exception
    while (!channel.isClosedForSend || !channel.isClosedForReceive) { /* stub */ }
}

fun generateData(channel: Channel<Int>) {
    CoroutineScope(Dispatchers.IO).launch {
        var counter = 0
        while (++counter <= 10) {
            println("$this send number: $counter")
            channel.send(counter)
            delay(1000L)
        }
    }
}

fun readData(channel: Channel<Int>) {
    CoroutineScope(Dispatchers.IO).launch {
        channel.receiveAsFlow().collectLatest {
            println("$this   get value: $it")
            if (it == 10) {
                channel.close(Exception("Done"))
            }
        }
    }
}