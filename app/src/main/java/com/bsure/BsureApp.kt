package com.bsure

import android.app.Application

class BsureApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BsureApp
            private set
    }
}