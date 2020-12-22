package com.lieni.livedatabus

import androidx.lifecycle.*

class EventLiveData<T>(val eventName: String) : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        doObserve(owner, observer, false)
    }

    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        doObserve(owner, observer, true)
    }

    private fun doObserve(owner: LifecycleOwner, observer: Observer<in T>, isSticky: Boolean) {
        super.observe(owner, StickyObserver(this, isSticky, observer))

        owner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    LiveEventBus.get().removeEvent(eventName)
                }
            }
        })
    }
}