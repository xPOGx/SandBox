package idp.android.kotlin.coroutines

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * "Тестування coroutines
 *     - створити тестовий клас для перевірки роботи coroutines;
 *     - використовуйте runBlocking test runner для тестування suspending functions;
 *     - перевіряйте успішне виконання та обробку помилок coroutines."
 */
class SuspendTest {
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun test() = runTest {
        launch(Dispatchers.Main) {
            suspendFun()
        }.apply {
            invokeOnCompletion {
                if (it != null) {
                    println("Failed with exception: ${it::class.java.simpleName} ${it.message}")
                } else {
                    println("Successfully finished")
                }
            }
        }
    }
}

suspend fun suspendFun() {
//    throw RuntimeException("Test error")
    println("Fun in process ...")
    delay(1000L)
    println("I'M DONE!")
}