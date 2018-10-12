package com.clemick.expenses.api.request

import com.clemick.expenses.model.ExpenseCategory
import com.clemick.expenses.model.Store
import javax.money.MonetaryAmount

data class CreateExpenseRequest(
        val amount : MonetaryAmount,
        val category : ExpenseCategory,
        val date : String,
        val store: Store)
