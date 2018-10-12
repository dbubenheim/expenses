package com.clemick.expenses.query

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class AccountQuery(@TargetAggregateIdentifier val id: String)