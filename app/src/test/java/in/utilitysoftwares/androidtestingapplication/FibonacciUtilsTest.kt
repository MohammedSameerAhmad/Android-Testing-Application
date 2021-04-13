package `in`.utilitysoftwares.androidtestingapplication

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FibonacciUtilsTest{

    @Test
    fun testFibonacci_correctResult_correctResult(){
        val result = FibonacciUtils.fib(10)
        assertThat(result).isEqualTo(55)
    }

    @Test
    fun testFibonacci_correctResult_passingZero(){
        val result = FibonacciUtils.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun testFibonacci_correctResult_passingOne(){
        val result = FibonacciUtils.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun testFibonacci_negativeResult_passingNegativeNumber(){
        val result = FibonacciUtils.fib(-10)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun testBraces_false_doubleBraces(){
        val result = FibonacciUtils.checkBraces("(a * b))")
        assertThat(result).isFalse()
    }

    @Test
    fun testBraces_true_correctBraces(){
        val result = FibonacciUtils.checkBraces("(a * b)")
        assertThat(result).isTrue()
    }

    @Test
    fun testBraces_false_empty(){
        val result = FibonacciUtils.checkBraces("")
        assertThat(result).isFalse()
    }

}