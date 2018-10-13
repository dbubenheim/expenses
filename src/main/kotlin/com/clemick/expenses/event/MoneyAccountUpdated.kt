package com.clemick.expenses.event

import com.clemick.expenses.model.Owner

data class MoneyAccountUpdated(val id: String, val name: String, val owner: Owner)
