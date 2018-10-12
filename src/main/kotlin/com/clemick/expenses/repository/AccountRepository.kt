package com.clemick.expenses.repository

import com.clemick.expenses.view.MoneyAccountView
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<MoneyAccountView, String>