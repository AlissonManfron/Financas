package br.com.alisson.financas.data.local.dao

import androidx.room.*
import br.com.alisson.financas.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM `transaction`")
    suspend fun getAll(): List<TransactionEntity>

    @Delete
    suspend fun delete(transaction: TransactionEntity)
}