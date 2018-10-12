package com.clemick.expenses.aggregate

import com.clemick.expenses.api.CreateCommand
import com.clemick.expenses.event.AccountCreated
import com.clemick.expenses.model.Owner
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aggregate
class Account {

    @AggregateIdentifier
    var id : String? = null

    var owner: Owner? = null

    var name: String? = null

    @CommandHandler
    constructor(command: CreateCommand) {
        logger.info("creating account...")
        AggregateLifecycle.apply(AccountCreated(command.id, command.name, command.owner))
    }

    @EventSourcingHandler
    fun on(event: AccountCreated) {
        logger.info("account created")

        this.id = event.id
        this.name = event.name
        this.owner = event.owner
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(Account::class.java)
    }
}