package com.clemick.expenses.event

import com.clemick.expenses.model.Owner

data class AccountCreated(val id: String, val name: String, val owner: Owner)