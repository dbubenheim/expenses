package com.clemick.expenses.view

import org.springframework.data.annotation.Id

data class MoneyAccountView(@Id val id: String, val name : String)