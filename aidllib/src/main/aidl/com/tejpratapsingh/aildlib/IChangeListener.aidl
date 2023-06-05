// ICalculator.aidl
package com.tejpratapsingh.aildlib;

import com.tejpratapsingh.aildlib.IChangeListener;
// Declare any non-default types here with import statements

interface IChangeListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onAdded(int result);
}