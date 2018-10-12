package com.clemick.expenses.query

import com.clemick.expenses.exception.AccountNotFoundException
import com.clemick.expenses.repository.AccountRepository
import com.clemick.expenses.view.AccountView
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class AccountQueryListener(val accountRepository: AccountRepository) {

    @QueryHandler
    fun on(query: AccountQuery): AccountView {
        return accountRepository.findById(query.id).orElseThrow { AccountNotFoundException(query.id) }
    }
}