package com.clemick.expenses.aggregate

import com.clemick.expenses.command.CreateExpenseCommand
import com.clemick.expenses.event.ExpenseCreatedEvent
import com.clemick.expenses.model.ExpenseCategory
import com.clemick.expenses.model.Store
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import javax.money.MonetaryAmount

@Aggregate
class Expense {

    @AggregateIdentifier
    lateinit var id: String

    lateinit var amount: MonetaryAmount

    lateinit var date: String

    lateinit var category: ExpenseCategory

    lateinit var store: Store

    constructor(command: CreateExpenseCommand) {

        apply(ExpenseCreatedEvent(
                command.id,
                command.amount,
                command.date,
                command.category,
                command.store))
    }

    @EventSourcingHandler
    fun on(event: ExpenseCreatedEvent) {

        this.id = event.id
        this.amount = event.amount
        this.date = event.date
        this.category = event.category
        this.store = event.store
    }
}
