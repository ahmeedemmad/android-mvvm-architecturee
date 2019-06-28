package net.simplifiedcoding.mvvmsampleapp

import android.app.Application
import net.simplifiedcoding.mvvmsampleapp.data.db.AppDatabase
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi
import net.simplifiedcoding.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import net.simplifiedcoding.mvvmsampleapp.data.repositories.UserRepository
import net.simplifiedcoding.mvvmsampleapp.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind<NetworkConnectionInterceptor>() with singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }

}