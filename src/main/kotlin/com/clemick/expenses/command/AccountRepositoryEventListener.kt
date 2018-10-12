package com.clemick.expenses.command

import com.clemick.expenses.api.ExpensesController
import com.clemick.expenses.event.AccountCreated
import com.clemick.expenses.repository.AccountRepository
import com.clemick.expenses.view.AccountView
import org.axonframework.eventhandling.EventHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountRepositoryEventListener(val accountRepository: AccountRepository) {

    @EventHandler
    fun on(event: AccountCreated) {
        accountRepository.save(AccountView(event.id, event.name))
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(ExpensesController::class.java)
    }
}