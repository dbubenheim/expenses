package com.clemick.expenses.event

import com.clemick.expenses.model.ExpenseCategory
import com.clemick.expenses.model.Store
import javax.money.MonetaryAmount

data class ExpenseCreatedEvent(
        val id: String,
        //val title: String,
        val amount: MonetaryAmount,
        val date: String,
        val category: ExpenseCategory,
        val store: Store)