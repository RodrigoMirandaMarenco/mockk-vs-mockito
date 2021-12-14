package com.rmiranda.mockkvsmockito

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.pow

/**
 * Created by rodrigomiranda on 14/12/21.
 * Applaudo Studios
 */
class ArmstrongUnitTest {

    @Test
    fun `is Armstrong number`() {
        assertTrue(0.isArmstrongNumber())
        assertTrue(1.isArmstrongNumber())
        assertTrue(153.isArmstrongNumber())
        assertTrue(370.isArmstrongNumber())
        assertTrue(371.isArmstrongNumber())
        assertTrue(407.isArmstrongNumber())
        assertTrue(1634.isArmstrongNumber())
        assertTrue(8208.isArmstrongNumber())
        assertTrue(8208.isArmstrongNumber())
        assertTrue(9474.isArmstrongNumber())
        assertFalse(154.isArmstrongNumber())
        assertFalse(155.isArmstrongNumber())
    }

    private fun Int.isArmstrongNumber(): Boolean {
        val modDelimiter = 10.0
        val digitList = mutableListOf<Int>()
        var sum = 0
        var index = 0

        while (sum < this) {
            val digit =
                (((this % (modDelimiter.pow(index + 1))) - (digitList.sum())) / (modDelimiter.pow(
                    index
                ))).toInt()
            digitList.add(digit)
            sum += digit * (modDelimiter.pow(index)).toInt()
            index++
        }

        return this == digitList.sumOf { it.toDouble().pow(digitList.size).toInt() }
    }
}