package com.clemick.expenses.query

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class MoneyAccountQuery(@TargetAggregateIdentifier val id: String)