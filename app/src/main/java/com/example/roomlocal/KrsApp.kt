package com.example.roomlocal

import android.app.Application
import com.example.roomlocal.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    //fungsi untuk menyimpan instance ContaineraApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)
    }

}