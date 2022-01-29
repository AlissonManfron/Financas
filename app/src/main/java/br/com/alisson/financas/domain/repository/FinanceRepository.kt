package br.com.alisson.financas.domain.repository

import br.com.alisson.financas.domain.model.Finance

interface FinanceRepository {

    suspend fun createFinance(finance: Finance)

    suspend fun deleteFinance(finance: Finance)

    suspend fun updateFinance(finance: Finance)

    suspend fun getFinance(): Finance

}