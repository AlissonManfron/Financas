package br.com.alisson.financas.presenter.activity.home

import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.model.Transaction

sealed class HomeViewState {

    class DashboardViewState(finance: Finance): HomeViewState() {
        val revenue: String = finance.getRevenueRS()
        val expense: String = finance.getExpenseRS()
        val total: String = finance.getTotalRS()
    }

    data class ListViewState(
        val isLoading: Boolean,
        val items: List<Transaction>
    ): HomeViewState()
}