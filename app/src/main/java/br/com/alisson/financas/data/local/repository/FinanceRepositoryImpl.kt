package br.com.alisson.financas.data.local.repository

import br.com.alisson.financas.data.local.dao.FinanceDao
import br.com.alisson.financas.data.mapper.toFinance
import br.com.alisson.financas.data.mapper.toFinanceEntity
import br.com.alisson.financas.domain.model.Finance
import br.com.alisson.financas.domain.repository.FinanceRepository

class FinanceRepositoryImpl(
    private val financeDao: FinanceDao
) : FinanceRepository {
    private val ID = 1L

    override suspend fun createFinance(finance: Finance) {
        financeDao.save(finance.toFinanceEntity())
    }

    override suspend fun deleteFinance(finance: Finance) {
        financeDao.delete(finance.toFinanceEntity())
    }

    override suspend fun updateFinance(finance: Finance) {
        financeDao.updateFinance(finance.toFinanceEntity())
    }

    override suspend fun getFinance(): Finance {
        return try {
            financeDao.getFinance(ID).toFinance()
        } catch (e: Exception) {
            return Finance(ID)
        }
    }

}