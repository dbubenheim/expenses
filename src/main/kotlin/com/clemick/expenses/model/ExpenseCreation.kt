package com.clemick.expenses.model

import javax.money.MonetaryAmount

data class ExpenseCreation(
        val amount : MonetaryAmount,
        val category : ExpenseCategory,
        val date : String,
        val store: Store)
