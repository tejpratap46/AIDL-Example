package com.tejpratapsingh.aidlserver.aidl

import com.tejpratapsingh.aildlib.ICalculator

object CalculatorImpl : ICalculator.Stub() {

    private const val TAG = "CalculatorImpl"

    override fun add(a: Int, b: Int): Int {
        return a + b
    }

}