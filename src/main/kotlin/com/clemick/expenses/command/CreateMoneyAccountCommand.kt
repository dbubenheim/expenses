package com.clemick.expenses.command

import com.clemick.expenses.model.Owner
import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateMoneyAccountCommand(@TargetAggregateIdentifier val id: String, val name : String, val owner: Owner)