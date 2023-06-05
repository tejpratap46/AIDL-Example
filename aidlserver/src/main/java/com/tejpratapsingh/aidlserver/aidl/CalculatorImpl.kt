package com.tejpratapsingh.aidlserver.aidl

import com.tejpratapsingh.aildlib.ICalculator
import com.tejpratapsingh.aildlib.IChangeListener
import java.util.concurrent.CopyOnWriteArrayList

object CalculatorImpl : ICalculator.Stub() {

    private const val TAG = "CalculatorImpl"

    private val callbacks: CopyOnWriteArrayList<IChangeListener> = CopyOnWriteArrayList()

    override fun registerListener(cb: IChangeListener) {
        callbacks.addIfAbsent(cb)
    }

    override fun unRegisterListener(cb: IChangeListener) {
        callbacks.remove(cb)
    }

    override fun add(a: Int, b: Int) {
        callbacks.forEach {
            it.onAdded(a + b)
        }
    }
}