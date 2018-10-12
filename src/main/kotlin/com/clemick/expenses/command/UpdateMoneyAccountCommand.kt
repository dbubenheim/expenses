package com.clemick.expenses.command

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class UpdateMoneyAccountCommand(@TargetAggregateIdentifier val id: String, val name : String)