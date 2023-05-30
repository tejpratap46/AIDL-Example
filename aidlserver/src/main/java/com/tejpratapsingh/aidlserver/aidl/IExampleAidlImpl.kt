package com.tejpratapsingh.aidlserver.aidl

import android.util.Log
import com.tejpratapsingh.aildlib.IExampleAidlInterface

object IExampleAidlImpl : IExampleAidlInterface.Stub() {

    private const val TAG = "IExampleAidlImpl"

    override fun basicTypes(
        anInt: Int,
        aLong: Long,
        aBoolean: Boolean,
        aFloat: Float,
        aDouble: Double,
        aString: String?
    ) {
        Log.d(TAG, "basicTypes: called")
    }

}