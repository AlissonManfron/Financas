package br.com.alisson.financas.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.alisson.financas.R
import br.com.alisson.financas.data.extensions.toDateBR
import br.com.alisson.financas.data.extensions.toRS
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.data.util.FinanceCategory
import br.com.alisson.financas.databinding.TransactionItemBinding

class TransactionsListAdapter(
    private val context: Context
) : RecyclerView.Adapter<TransactionsListAdapter.ViewHolder>() {

    private var transactions: List<Transaction> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = TransactionItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction: Transaction = transactions[position]
        holder.bind(context, transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun setTransactions(transactions: List<Transaction>) {
        this.transactions = transactions
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(context: Context, transaction: Transaction) {
            itemBinding.transactionPrice.text = transaction.price.toRS()
            itemBinding.transactionCategory.text = transaction.description
            itemBinding.transactionDate.text = transaction.date.toDateBR()

            when (FinanceCategory.valueOf(transaction.category)) {
                FinanceCategory.REVENUE -> {
                    itemBinding.transactionIcon
                        .setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icone_transacao_item_receita_green))
                }
                FinanceCategory.EXPENSE -> {
                    itemBinding.transactionIcon
                        .setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icone_transacao_item_despesa))
                }
            }
        }
    }
}