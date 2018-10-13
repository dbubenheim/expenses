package com.clemick.expenses.view

import com.clemick.expenses.model.Owner
import org.springframework.data.annotation.Id

data class MoneyAccountView(@Id val id: String, val name : String, val owner: Owner)