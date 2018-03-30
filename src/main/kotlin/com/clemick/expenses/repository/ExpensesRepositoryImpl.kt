package com.clemick.expenses.repository

import com.clemick.expenses.model.Expense
import org.javamoney.moneta.Money
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

@Repository
class ExpensesRepositoryImpl : ExpensesRepository {

    override fun findAll() : List<Expense> {
        return listOf(
            Expense(UUID.randomUUID(), "Tanken", Money.of(60.60, "EUR"), Instant.now().toString()),
            Expense(UUID.randomUUID(), "Einkaufen Lidl", Money.of(90.34, "EUR"), Instant.now().toString()),
            Expense(UUID.randomUUID(), "Einkaufen Lidl", Money.of(86.22, "EUR"), Instant.now().toString()),
            Expense(UUID.randomUUID(), "DM Drogeriemarkt", Money.of(32.50, "EUR"), Instant.now().toString()),
            Expense(UUID.randomUUID(), "Baumarkt", Money.of(1250.34, "EUR"), Instant.now().toString())
        )
    }
}