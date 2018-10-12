package com.clemick.expenses.repository

import com.clemick.expenses.aggregate.Expense

interface ExpensesRepository {

    fun findAll(): List<Expense>
}
