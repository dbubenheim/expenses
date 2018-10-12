package com.clemick.expenses.api

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class UpdateCommand(@TargetAggregateIdentifier val id: String, val name : String)