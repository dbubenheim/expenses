package com.clemick.expenses.command

import com.clemick.expenses.model.ExpenseCategory
import com.clemick.expenses.model.Store
import org.axonframework.commandhandling.TargetAggregateIdentifier
import javax.money.MonetaryAmount

data class CreateExpenseCommand(
     @TargetAggregateIdentifier val id: String,
     val amount: MonetaryAmount,
     val category: ExpenseCategory,
     val store: Store,
     val date: String)