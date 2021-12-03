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
    val commonBitListFlipped: List<Int> = findMostCommonBitInColumnFlipped(initialMatrix, 0)
    val uncommonBitList: List<Int> = findLeastCommenBitInColumn(commonBitListFlipped)
    val oxygenArray: List<Int> = getOxygenList(initialMatrix, commonBitListFlipped)
    val co2Array: List<Int> = getCo2List(initialMatrix, uncommonBitList)
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

fun getCo2List(initialMatrix: List<List<Char>>, initialUncommonBitList: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    var uncommonBitList = mutableListOf<Int>()
    uncommonBitList.addAll(initialUncommonBitList)

    var matrix = mutableListOf<MutableList<Char>>()
    for (row in initialMatrix) {
        matrix.add(row.toMutableList())
    }

    for (i in uncommonBitList.indices) {


        println("column:: $i")
        println("uncommon bit list:: $uncommonBitList")
        println("uncommon bit:: ${uncommonBitList[i]}")
        for (row in matrix) {
            println(row)
        }



        matrix = matrix.filter { row -> row[i].digitToInt() == uncommonBitList[i] }.toMutableList()
        uncommonBitList = findLeastCommenBitInColumn(findMostCommonBitInColumnFlipped(matrix, 0)).toMutableList()

        if (matrix.size == 1) {
            for (j in matrix[0]) {
                result.add(j.digitToInt())
            }
            return result
        }

    }
    throw error("co2 not identified")
}

fun getOxygenList(initialMatrix: List<List<Char>>, initialCommonBitList: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    var commonBitList = mutableListOf<Int>()
    commonBitList.addAll(initialCommonBitList)

    var matrix = mutableListOf<MutableList<Char>>()
    for (col in initialMatrix) {
        matrix.add(col.toMutableList())
    }

    for (i in commonBitList.indices) {


        println("column:: $i")
        println("common bit list:: $commonBitList")
        println("common bit:: ${commonBitList[i]}")
/*
        for (row in matrix) {
            println(row)
        }
*/

        matrix = matrix.filter { row -> row[i].digitToInt() == commonBitList[i] }.toMutableList()

        println("remaining rows:: ${matrix.size}")

        commonBitList = findMostCommonBitInColumnFlipped(matrix, 1).toMutableList()

        if (matrix.size == 1) {
            for (j in matrix[0]) {
                result.add(j.digitToInt())
            }
            return result
        }

    }
    throw error("oxygen not identified")
}

fun findLeastCommenBitInColumn(commonBitList: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (i in commonBitList) {
        if (i == 0) result.add(1)
        else result.add(0)
    }

    return result
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

fun findMostCommonBitInColumnFlipped(initialMatrix: List<List<Char>>, keep: Int): List<Int> {
    val matrix = mutableListOf<MutableList<Char>>()
    for (row in initialMatrix) {
        matrix.add(row.toMutableList())
    }

    val result = mutableListOf<Int>()
    val zeroes = mutableListOf<Int>()
    val ones = mutableListOf<Int>()

    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            if (zeroes.size < col + 1) {
                zeroes.add(0)
            }
            if (ones.size < col + 1) {
                ones.add(0)
            }

            if (matrix[row][col] == '0') {
                zeroes[col]++
            }
            else ones[col] ++
        }
    }

    for (i in zeroes.indices) {
        if (zeroes[i] == ones[i]) result.add(keep)
        else if (zeroes[i] > ones[i]) result.add(0)
        else result.add(1)
    }

    return result
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
