package com.clemick.expenses.repository

import com.clemick.expenses.view.AccountView
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<AccountView, String>