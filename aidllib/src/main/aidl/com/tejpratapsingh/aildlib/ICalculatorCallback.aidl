// ICalculatorCallback.aidl
package com.tejpratapsingh.aildlib;

import com.tejpratapsingh.aildlib.ICalculatorCallback;
// Declare any non-default types here with import statements

interface ICalculatorCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onAdded(int result);
}