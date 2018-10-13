package com.clemick.expenses.aggregate

import com.clemick.expenses.command.CreateMoneyAccountCommand
import com.clemick.expenses.command.UpdateMoneyAccountCommand
import com.clemick.expenses.event.MoneyAccountCreated
import com.clemick.expenses.event.MoneyAccountUpdated
import com.clemick.expenses.model.Owner
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aggregate
class MoneyAccount {

    @AggregateIdentifier
    var id : String? = null

    var owner: Owner? = null

    var name: String? = null

    constructor()

    @CommandHandler
    constructor(command: CreateMoneyAccountCommand) {
        logger.info("creating account...")
        apply(MoneyAccountCreated(command.id, command.name, command.owner))
    }

    @CommandHandler
    fun on(command: UpdateMoneyAccountCommand) {
        logger.info("updating account...")
        apply(MoneyAccountUpdated(command.id, command.name, command.owner))
    }

    @EventSourcingHandler
    fun on(event: MoneyAccountCreated) {
        logger.info("account created")

        this.id = event.id
        this.name = event.name
        this.owner = event.owner
    }

    @EventSourcingHandler
    fun on(event: MoneyAccountUpdated) {
        logger.info("account updated")

        this.id = event.id
        this.name = event.name
        this.owner = event.owner
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MoneyAccount::class.java)
    }
}