// ICalculator.aidl
package com.tejpratapsingh.aildlib;

import com.tejpratapsingh.aildlib.IChangeListener;

// Declare any non-default types here with import statements

interface ICalculator {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void registerListener(IChangeListener cb);
    void unRegisterListener(IChangeListener cb);

    void add(int a, int b);
}