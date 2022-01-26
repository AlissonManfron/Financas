package br.com.alisson.financas.presenter.activity.home

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.model.Transaction

sealed class HomeViewState {

    class DashboardViewState(finance: Finance): HomeViewState() {
        val revenue: Double = finance.revenue
        val expense: Double = finance.expense
        val total: Double = finance.total
    }

    data class ListViewState(
        val isLoading: Boolean,
        val items: List<Transaction>
    ): HomeViewState()
}