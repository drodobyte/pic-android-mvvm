package com.drodobyte.core.data.model

data class Amount(
    val value: Float,
    val what: What
) {

    enum class What {
        Gr, Oz, Cup
    }

    companion object {
        val gr100 = Amount(100f, What.Gr)
        val oz2 = Amount(2f, What.Oz)
        val cup = Amount(1f, What.Cup)
    }
}
