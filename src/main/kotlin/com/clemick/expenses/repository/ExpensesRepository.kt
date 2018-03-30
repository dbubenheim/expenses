package com.clemick.expenses.repository

import com.clemick.expenses.model.Expense

interface ExpensesRepository {

    fun findAll(): List<Expense>
}
