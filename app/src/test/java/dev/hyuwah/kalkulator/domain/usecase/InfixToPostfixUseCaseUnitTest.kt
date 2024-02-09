package dev.hyuwah.kalkulator.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

class InfixToPostfixUseCaseUnitTest {

    private val infixToPostfixUseCase = InfixToPostfixUseCase()

    @Test
    fun addition_isCorrect() {
        val input = "5 + 12"
        val expected = listOf("5", "12", "+")
        val actual = infixToPostfixUseCase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun combination_1() {
        val input = "15 + 6 * 2 - (3^0 - 7)"
        val expected = listOf("15", "6", "2", "*", "+", "3", "0", "^", "7", "-", "-")
        val actual = infixToPostfixUseCase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun combination_2() {
        val input = "9 * (5 / 2) + 88 - -50"
        val expected = listOf("9", "5", "2", "/", "*", "88", "+", "-50", "-")
        val actual = infixToPostfixUseCase(input)
        assertEquals(expected, actual)
    }
}