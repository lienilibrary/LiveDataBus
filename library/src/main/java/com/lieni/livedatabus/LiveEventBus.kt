package com.lieni.livedatabus

class LiveEventBus {

    private var bus = hashMapOf<String, EventLiveData<Any>>()

    companion object {
        @Volatile
        private var instance: LiveEventBus? = null
        fun get(): LiveEventBus = instance ?: synchronized(this) {
            instance ?: LiveEventBus().also { instance = it }
        }
    }

    fun with(eventName: String): EventLiveData<Any> = with(eventName, Any::class.java)

    @Suppress("UNCHECKED_CAST")
    fun <T> with(eventName: String, type: Class<T>): EventLiveData<T> {
        if (!bus.containsKey(eventName)) {
            bus[eventName] = EventLiveData(eventName)
        }
        return bus[eventName] as EventLiveData<T>
    }

    fun removeEvent(eventName: String) {
        bus.remove(eventName)
    }
}