package br.com.alisson.financas.data.di

import android.app.Application
import androidx.room.Room
import br.com.alisson.financas.data.local.AppDatabase
import br.com.alisson.financas.data.local.dao.FinanceDao
import br.com.alisson.financas.data.local.dao.TransactionDao
import br.com.alisson.financas.data.local.repository.FinanceRepositoryImpl
import br.com.alisson.financas.data.local.repository.TransactionsRepositoryImpl
import br.com.alisson.financas.domain.repository.FinanceRepository
import br.com.alisson.financas.domain.repository.TransactionsRepository
import br.com.alisson.financas.domain.usecase.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

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
}