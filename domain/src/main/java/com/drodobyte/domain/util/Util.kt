package com.drodobyte.domain.util

operator fun ClosedFloatingPointRange<Float>.times(n: Int) =
    IntRange((start * n).toInt(), (endInclusive * n).toInt())
