package com.clemick.expenses.api.request

import com.clemick.expenses.model.Owner

data class CreateMoneyAccountRequest(
        val name: String,
        val owner: Owner)