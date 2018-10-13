package com.clemick.expenses.listener

import com.clemick.expenses.api.MoneyAccountController
import com.clemick.expenses.event.MoneyAccountCreated
import com.clemick.expenses.event.MoneyAccountDeleted
import com.clemick.expenses.event.MoneyAccountUpdated
import com.clemick.expenses.repository.AccountRepository
import com.clemick.expenses.view.MoneyAccountView
import org.axonframework.eventhandling.EventHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountRepositoryEventListener(val accountRepository: AccountRepository) {

    @EventHandler
    fun on(event: MoneyAccountCreated) {
        accountRepository.save(MoneyAccountView(event.id, event.name, event.owner))
    }

    @EventHandler
    fun on(event: MoneyAccountUpdated) {
        accountRepository.save(MoneyAccountView(event.id, event.name, event.owner))
    }

    @EventHandler
    fun on(event: MoneyAccountDeleted) {
        accountRepository.deleteById(event.id)
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MoneyAccountController::class.java)
    }
}