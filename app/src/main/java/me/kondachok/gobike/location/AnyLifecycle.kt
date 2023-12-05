package me.kondachok.gobike.location

interface AnyLifecycle<T> {

    fun start(args: T)

    fun stop()
}

fun AnyLifecycle<Unit>.start() = this.start(Unit)
