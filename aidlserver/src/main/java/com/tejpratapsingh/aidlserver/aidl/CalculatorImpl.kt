package com.tejpratapsingh.aidlserver.aidl

import com.tejpratapsingh.aildlib.ICalculator
import com.tejpratapsingh.aildlib.ICalculatorCallback
import java.util.concurrent.CopyOnWriteArrayList

object CalculatorImpl : ICalculator.Stub() {

    private const val TAG = "CalculatorImpl"

    private val callbacks: CopyOnWriteArrayList<ICalculatorCallback> = CopyOnWriteArrayList()

    override fun registerListener(cb: ICalculatorCallback) {
        callbacks.addIfAbsent(cb)
    }

    override fun unRegisterListener(cb: ICalculatorCallback) {
        callbacks.remove(cb)
    }

    override fun add(a: Int, b: Int) {
        callbacks.forEach {
            it.onAdded(a + b)
        }
    }
}