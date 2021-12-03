import kotlin.math.pow

fun main() {
    // Part 1
    val input: List<String> = readInput("Day03")
    val matrix: List<List<Char>> = generateMatrix(input)
    val commonBitList: List<Int> = findMostCommonBitInColumn(matrix)
    val gammaArray: List<Int> = transformToReversedBinaryArray(commonBitList, "gamma")
    val epsilonArray: List<Int> = transformToReversedBinaryArray(commonBitList, "epsilon")
    val gamma: Int = binaryListToDecimal(gammaArray)
    val epsilon: Int = binaryListToDecimal(epsilonArray)
    val part1 = gamma.times(epsilon)
    println("Part 1:: $part1")

    // Part 2
    val initialMatrix: List<List<Char>> = generateFlippedMatrix(input)
    println("flipped matrix")
    for (row in initialMatrix) {
        println(row)
    }
    val oxygenArray: List<Int> = getOxygenList(initialMatrix)
    val co2Array: List<Int> = getCo2List(initialMatrix)
    val oxygen: Int = binaryListToDecimal(oxygenArray.reversed())
    val co2: Int = binaryListToDecimal(co2Array.reversed())
    val part2 = oxygen.times(co2)
    println("Part 2:: $part2")

}

fun generateFlippedMatrix(input: List<String>): List<List<Char>> {
    val matrix = mutableListOf<List<Char>>()

    for (line in input) {
        val row = mutableListOf<Char>()
        for (i in 0..11) {
            row.add(line.toCharArray()[i])
        }
        matrix.add(row)
    }

    return matrix

}

fun getCo2List(initialMatrix: List<List<Char>>): List<Int> {
    val result = mutableListOf<Int>()


    var matrix = mutableListOf<MutableList<Char>>()
    for (row in initialMatrix) {
        matrix.add(row.toMutableList())
    }

    for (i in 0..11) {
        val commonBit = findMostCommonBitInColumnFlipped(matrix, i, 1)
        var uncommonBit: Int
        if (commonBit == 0) uncommonBit = 1
        else uncommonBit = 0


        println("column:: $i")
        println("uncommon bit:: ${uncommonBit}")
        for (row in matrix) {
            println(row)
        }



        matrix = matrix.filter { row -> row[i].digitToInt() == uncommonBit }.toMutableList()

        if (matrix.size == 1) {
            for (j in matrix[0]) {
                result.add(j.digitToInt())
            }
            return result
        }

    }
    throw error("co2 not identified")
}

fun getOxygenList(initialMatrix: List<List<Char>>): List<Int> {
    val result = mutableListOf<Int>()

    var matrix = mutableListOf<MutableList<Char>>()
    for (col in initialMatrix) {
        matrix.add(col.toMutableList())
    }

    for (i in 0..11) {
        val commonBit = findMostCommonBitInColumnFlipped(matrix, i, 1)


        println("column:: $i")
        println("common bit:: ${commonBit}")
/*
        for (row in matrix) {
            println(row)
        }
*/

        matrix = matrix.filter { row -> row[i].digitToInt() == commonBit }.toMutableList()

        println("remaining rows:: ${matrix.size}")

        if (matrix.size == 1) {
            for (j in matrix[0]) {
                result.add(j.digitToInt())
            }
            return result
        }

    }
    throw error("oxygen not identified")
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

fun findMostCommonBitInColumnFlipped(initialMatrix: List<List<Char>>, column: Int, keep: Int): Int {
    val matrix = mutableListOf<MutableList<Char>>()
    for (row in initialMatrix) {
        matrix.add(row.toMutableList())
    }

    var zeroes = 0
    var ones = 0

    for (row in matrix.indices) {
        if (matrix[row][column] == '0') {
            zeroes++
        } else ones++
    }


    if (zeroes == ones) return keep
    else if (zeroes > ones) return 0
    else return 1
}

fun findMostCommonBitInColumn(matrix: List<List<Char>>): List<Int> {
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
