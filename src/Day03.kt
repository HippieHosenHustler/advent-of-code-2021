import kotlin.math.pow

fun main() {
    val input: List<String> = readInput("Day03")
    val matrix: List<List<Char>> = generateMatrix(input)
    val commonBitList: List<Int> = findMostCommenBitInRow(matrix)
    val gammaArray: List<Int> = transformToReversedBinaryArray(commonBitList, "gamma")
    val epsilonArray: List<Int> = transformToReversedBinaryArray(commonBitList, "epsilon")
    val gamma: Int = binaryListToDecimal(gammaArray)
    val epsilon: Int = binaryListToDecimal(epsilonArray)
    val part1 = gamma.times(epsilon)
    println("Part 1:: $part1")



}

fun binaryListToDecimal(input: List<Int>): Int {
    var result = 0

    for (i in input.indices) {
        val power = 2.0.pow(i)
        result = result.plus(input[i].times(power)).toInt()
    }

    return result
}

fun transformToReversedBinaryArray(commonBitList: List<Int>, s: String): List<Int> {
    val reversed = commonBitList.reversed()
    val result = mutableListOf<Int>()

    if (s == "gamma") {
        result.addAll(reversed)
    } else {
        for (i in reversed) {
            if (i == 0) result.add(1)
            else result.add(0)
        }
    }

    return result
}

fun findMostCommenBitInRow(matrix: List<List<Char>>): List<Int> {
    val result = mutableListOf<Int>()

    for (column in matrix) {
        var zeroes = 0
        var ones = 0

        for (line in column) {
            if (line == '0') {
                zeroes++
            } else {
                ones++
            }
        }

        if (zeroes > ones) {
            result.add(0)
        } else {
            result.add(1)
        }
    }

    return result
}

fun generateMatrix(input: List<String>): List<List<Char>> {
    val matrix = mutableListOf<List<Char>>()

    for (i in 0..11) {
        val column = mutableListOf<Char>()
        for (line in input) {
            column.add(line.toCharArray()[i])
        }
        matrix.add(column)
    }

    return matrix
}
