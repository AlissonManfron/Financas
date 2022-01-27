package br.com.alisson.financas.presenter.di

import br.com.alisson.financas.presenter.activity.home.HomeViewModel
import br.com.alisson.financas.presenter.adapter.TransactionsListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presenterModule = module {

    factory { TransactionsListAdapter(get()) }

    viewModel { HomeViewModel(get(), get(), get(), get()) }

}