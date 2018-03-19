package com.clemick.expenses.model

import java.util.*
import javax.money.MonetaryAmount

data class Expense(val id: UUID, val title: String, val price: MonetaryAmount, val date: String)