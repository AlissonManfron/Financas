package br.com.alisson.financas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alisson.financas.data.local.dao.FinanceDao
import br.com.alisson.financas.data.local.dao.TransactionDao
import br.com.alisson.financas.data.local.entity.FinanceEntity
import br.com.alisson.financas.data.local.entity.TransactionEntity

@Database(entities = [TransactionEntity::class, FinanceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun financeDao(): FinanceDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app_db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}