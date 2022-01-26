package br.com.alisson.financas.data.local.dao

import androidx.room.*
import br.com.alisson.financas.data.local.entity.FinanceEntity

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(finance: FinanceEntity)

    @Query("SELECT * FROM finance WHERE id = :id")
    suspend fun getFinance(id: Long): FinanceEntity

    @Delete
    suspend fun delete(finance: FinanceEntity)

    @Update
    suspend fun updateFinance(finance: FinanceEntity)
}