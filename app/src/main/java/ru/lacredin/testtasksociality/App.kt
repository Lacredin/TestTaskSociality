package ru.lacredin.testtasksociality

import android.app.Application
import ru.lacredin.testtasksociality.di.components.ApplicationComponent
import ru.lacredin.testtasksociality.di.components.DaggerApplicationComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.create()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}