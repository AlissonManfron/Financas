package br.com.alisson.financas.presenter.activity.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.usecase.CreateTransactionUseCase
import br.com.alisson.financas.domain.usecase.GetFinanceUseCase
import br.com.alisson.financas.domain.usecase.GetTransactionsUseCase
import br.com.alisson.financas.domain.usecase.UpdateFinanceUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFinanceUseCase: GetFinanceUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val updateFinanceUseCase: UpdateFinanceUseCase
) : ViewModel() {

    private val _homeViewState = MutableLiveData<HomeViewState>()
    val homeViewState = _homeViewState as LiveData<HomeViewState>

    fun getFinanceAndTransactions() {
        getFinance()
        getTransactions()
    }

    fun addTransactionAndUpdateFinance(transaction: Transaction) {
        addTransaction(transaction)
        updateFinance(transaction)
    }

    fun getTransactions() {
        viewModelScope.launch {
            val transactionList = getTransactionsUseCase()
            _homeViewState.value = HomeViewState.ListViewState(false, transactionList)
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            createTransactionUseCase(transaction)
        }
    }

    fun getFinance() {
        viewModelScope.launch {
            val finance = getFinanceUseCase()
            _homeViewState.value = HomeViewState.DashboardViewState(finance)
        }
    }

    fun updateFinance(transaction: Transaction) {
        viewModelScope.launch {
            updateFinanceUseCase(transaction)
        }
    }
}