package br.com.alisson.financas

import android.app.Application
import br.com.alisson.financas.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FinanceApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FinanceApplication)
            modules(appModule)
        }
    }
}