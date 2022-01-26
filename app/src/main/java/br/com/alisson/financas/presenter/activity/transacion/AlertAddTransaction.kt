package br.com.alisson.financas.presenter.activity.transacion

import android.app.Activity
import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.alisson.financas.R
import br.com.alisson.financas.domain.model.Transaction
import br.com.alisson.financas.data.util.FinanceCategory
import java.util.*

class AlertAddTransaction {

    companion object {
        private var alert: AlertDialog? = null

        fun show(activity: Activity, isRenenue: Boolean, callback: TransactionOnclickListener) {
            activity.let {
                val builder = AlertDialog.Builder(it)

                val inflater = activity.layoutInflater;
                val layout = inflater.inflate(R.layout.alert_add_transaction, null)

                val txtTitle = layout.findViewById<TextView>(R.id.title)
                val editDescription = layout.findViewById<EditText>(R.id.description)
                val editPrice = layout.findViewById<EditText>(R.id.price)
                val btnSave = layout.findViewById<Button>(R.id.btnSave)

                if (isRenenue) {
                    txtTitle.text = activity.getString(R.string.add_revenue)
                    editDescription.hint = activity.getString(R.string.revenue_hint)
                    txtTitle.setBackgroundColor(activity.getColor(R.color.revenue))
                    btnSave.setTextColor(activity.getColor(R.color.revenue))
                } else{
                    txtTitle.text = activity.getString(R.string.add_expense)
                    editDescription.hint = activity.getString(R.string.expense_hint)
                    txtTitle.setBackgroundColor(activity.getColor(R.color.expense))
                    btnSave.setTextColor(activity.getColor(R.color.expense))
                }

                btnSave.setOnClickListener {
                    val description = editDescription.text.toString()
                    val price = editPrice.text.toString().toDouble()
                    val category = if (isRenenue) FinanceCategory.REVENUE.name else FinanceCategory.EXPENSE.name

                    callback.onclickListener(Transaction(null, price, description, category, Calendar.getInstance()))
                    alert?.dismiss()
                }

                builder.setView(layout)
                builder.create()
                alert = builder.show()
            }
        }
    }

    interface TransactionOnclickListener {
        fun onclickListener(transaction: Transaction)
    }
}