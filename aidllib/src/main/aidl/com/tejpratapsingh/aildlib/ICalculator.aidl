// ICalculator.aidl
package com.tejpratapsingh.aildlib;

import com.tejpratapsingh.aildlib.ICalculatorCallback;

// Declare any non-default types here with import statements

interface ICalculator {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void registerListener(ICalculatorCallback cb);
    void unRegisterListener(ICalculatorCallback cb);

    void add(int a, int b);
}