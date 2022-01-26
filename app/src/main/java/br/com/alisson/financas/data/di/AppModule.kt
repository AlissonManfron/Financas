package br.com.alisson.financas.data.di

import android.app.Application
import androidx.room.Room
import br.com.alisson.financas.data.local.AppDatabase
import br.com.alisson.financas.data.local.dao.FinanceDao
import br.com.alisson.financas.data.local.dao.TransactionDao
import br.com.alisson.financas.data.local.repository.finance.FinanceRepository
import br.com.alisson.financas.data.local.repository.finance.FinanceRepositoryImpl
import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepository
import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepositoryImpl
import br.com.alisson.financas.domain.usecase.*
import br.com.alisson.financas.presenter.activity.home.HomeViewModel
import br.com.alisson.financas.presenter.adapter.TransactionsListAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return  database.transactionDao()
    }

    fun provideFinanceDao(database: AppDatabase): FinanceDao {
        return  database.financeDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideTransactionDao(get()) }
    single { provideFinanceDao(get()) }

    single<TransactionsRepository> { TransactionsRepositoryImpl(get()) }
    single<FinanceRepository> { FinanceRepositoryImpl(get()) }

    single<GetFinanceUseCase> { GetFinance(get()) }
    single<UpdateFinanceUseCase> { UpdateFinance(get()) }

    single<GetTransactionsUseCase> { GetTransactions(get()) }
    single<CreateTransactionUseCase> { CreateTransaction(get()) }

    factory { TransactionsListAdapter(get()) }

    viewModel { HomeViewModel(get(), get(), get(), get()) }
}