package br.com.alisson.financas.presenter.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alisson.financas.databinding.ActivityHomeBinding
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.presenter.activity.transacion.AlertAddTransaction
import br.com.alisson.financas.presenter.adapter.TransactionsListAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter: TransactionsListAdapter by inject()
    private lateinit var binding: ActivityHomeBinding

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
        binding.listaTransacoesListview.layoutManager = LinearLayoutManager(this)
        binding.listaTransacoesListview.adapter = adapter
    }

    private fun initObservableViews() {
        viewModel.homeViewState.observe(this, { viewState ->
            when (viewState) {
                is HomeViewState.DashboardViewState -> {
                    binding.listTransaction.resumeCardRevenue.text = viewState.revenue
                    binding.listTransaction.resumeCardExpense.text = viewState.expense
                    binding.listTransaction.resumeCardTotal.text = viewState.total
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