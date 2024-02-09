package dev.hyuwah.kalkulator.domain.model

enum class Operator(val symbol: Char) {
    LEFT_PARENTHESES('('),
    RIGHT_PARENTHESES(')'),
    UNARY_MINUS('-'),
    EXPONENT('^'),
    MULTIPLY('*'),
    DIVIDE('/'),
    MODULUS('%'),
    ADD('+'),
    SUBSTRACT('-');

    fun symbol(): String = symbol.toString()

    companion object {
        fun isBinaryOperator(c: Char): Boolean {
            val binaryOperator = listOf(
                EXPONENT.symbol,
                MULTIPLY.symbol,
                DIVIDE.symbol,
                MODULUS.symbol,
                ADD.symbol,
                SUBSTRACT.symbol
            )
            return binaryOperator.contains(c)
        }

        fun isBinaryOperator(s: String): Boolean {
            val binaryOperator = listOf(
                EXPONENT.symbol,
                MULTIPLY.symbol,
                DIVIDE.symbol,
                MODULUS.symbol,
                ADD.symbol,
                SUBSTRACT.symbol
            )
            return binaryOperator.map { it.toString() }.contains(s)
        }

        fun precedence(c: Char?): Int {
            return when (c) {
                EXPONENT.symbol -> 3
                MULTIPLY.symbol, DIVIDE.symbol, MODULUS.symbol -> 2
                ADD.symbol, SUBSTRACT.symbol -> 1
                else -> 0
            }
        }

        fun isNegativeSign(c: Char, prevC: Char): Boolean {
            val validOperatorPrefixingNegativeSign = listOf(
                LEFT_PARENTHESES.symbol,
                EXPONENT.symbol,
                MULTIPLY.symbol,
                DIVIDE.symbol,
                ADD.symbol,
                SUBSTRACT.symbol
            )
            return c == UNARY_MINUS.symbol && validOperatorPrefixingNegativeSign.contains(prevC)
        }
    }
}