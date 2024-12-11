package com.example.roomlocal

import android.app.Application
import com.example.roomlocal.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    //fungsi untuk menyimpan instance ContaineraApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //memmbuat instance ContainerApp
        containerApp = ContainerApp(this)
        //instance adalah object yang dibuat dari class
    }

}