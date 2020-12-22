package com.lieni.livedatabus

import androidx.lifecycle.Observer

class StickyObserver<T>(
    private val liveData: EventLiveData<T>,
    private val isSticky: Boolean,
    private val observer: Observer<in T>
) : Observer<T> {

    private var version: Int = getEventVersion()

    override fun onChanged(t: T) {
        val eventVersion = getEventVersion()
        if (version >= eventVersion) {
            if (isSticky) {
                observer.onChanged(t)
            }
            return
        }
        version = eventVersion
        observer.onChanged(t)
    }

    private fun getEventVersion(): Int =
        liveData.javaClass.superclass?.superclass?.getDeclaredField("mVersion").also {
            it?.isAccessible = true
        }?.get(liveData) as Int
}