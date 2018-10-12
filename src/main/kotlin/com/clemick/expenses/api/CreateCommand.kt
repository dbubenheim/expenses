package com.clemick.expenses.api

import com.clemick.expenses.model.Owner
import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateCommand(@TargetAggregateIdentifier val id: String, val name : String, val owner: Owner)