package br.com.alisson.financas.presenter.activity.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alisson.financas.data.extensions.toExpenseRS
import br.com.alisson.financas.data.extensions.toRS
import br.com.alisson.financas.data.extensions.toRevenueRS
import br.com.alisson.financas.data.local.AppDatabase
import br.com.alisson.financas.data.local.repository.finance.FinanceRepositoryImpl
import br.com.alisson.financas.data.local.repository.transaction.TransactionsRepositoryImpl
import br.com.alisson.financas.databinding.ActivityHomeBinding
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.domain.usecase.CreateTransaction
import br.com.alisson.financas.domain.usecase.GetFinance
import br.com.alisson.financas.domain.usecase.GetTransactions
import br.com.alisson.financas.domain.usecase.UpdateFinance
import br.com.alisson.financas.presenter.activity.transacion.AlertAddTransaction
import br.com.alisson.financas.presenter.adapter.TransactionsListAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels(
        factoryProducer = {
            val dataBase = AppDatabase.getDatabase(this)
            val trRepository = TransactionsRepositoryImpl(dataBase.transactionDao())
            val fnRepository = FinanceRepositoryImpl(dataBase.financeDao())

            HomeViewModel.HomeViewModelFactory(
                GetFinance(fnRepository),
                GetTransactions(trRepository),
                CreateTransaction(trRepository),
                UpdateFinance(fnRepository)
            )
        }
    )

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: TransactionsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        initObservableViews()

        viewModel.getFinanceAndTransactions()
    }

    private fun initRecyclerView() {
        adapter = TransactionsListAdapter(emptyList(), this)
        binding.listaTransacoesListview.layoutManager = LinearLayoutManager(this)
        binding.listaTransacoesListview.adapter = adapter
    }

    private fun initObservableViews() {
        viewModel.homeViewState.observe(this, { viewState ->
            when (viewState) {
                is HomeViewState.DashboardViewState -> {
                    binding.listTransaction.resumeCardRevenue.text = viewState.revenue.toRevenueRS()
                    binding.listTransaction.resumeCardExpense.text = viewState.expense.toExpenseRS()
                    binding.listTransaction.resumeCardTotal.text = viewState.total.toRS()
                }

                is HomeViewState.ListViewState -> {
                    viewState.items.let { transactionsList ->
                        adapter.setTransactions(transactionsList)
                    }
                }
            }
        })

        binding.fabTransactionAddRevenue.setOnClickListener {
            binding.fabBtn.close(true)
            showAlertAddTransaction(true)
        }

        binding.fabTransactionAddExpense.setOnClickListener {
            binding.fabBtn.close(true)
            showAlertAddTransaction(false)
        }
    }

    private fun showAlertAddTransaction(isRenenue: Boolean) {
        AlertAddTransaction.show(this, isRenenue, object : AlertAddTransaction.TransactionOnclickListener {
            override fun onclickListener(transaction: Transaction) {
                viewModel.addTransactionAndUpdateFinance(transaction)
                lifecycleScope.launch {
                    delay(500)
                    viewModel.getFinanceAndTransactions()
                }
            }
        })
    }
}