package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
fun max(values: List<Int>): Int = if (values.isNotEmpty()) {
    var inner_max: Int = values[0]
    values.forEach { if (it > inner_max) inner_max = it }
    inner_max } else 0

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long = if (values.isNotEmpty()) {
    var inner_sum: Long = 0
    values.forEach { inner_sum += it }
    inner_sum } else 0