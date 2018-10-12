package com.clemick.expenses.api

import com.clemick.expenses.command.CreateExpenseCommand
import com.clemick.expenses.api.request.CreateExpenseRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/expense/api/v1")
class ExpenseController(private val commandGateway: CommandGateway) {

    @PostMapping(produces = ["application/json"])
    @ResponseBody
    fun create(@RequestBody createExpenseRequest: CreateExpenseRequest) : CompletableFuture<String> {
        logger.info("create.....")
        return commandGateway.send(
                CreateExpenseCommand(
                        UUID.randomUUID().toString(),
                        createExpenseRequest.amount,
                        createExpenseRequest.category,
                        createExpenseRequest.store,
                        LocalDateTime.now().toString()))
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(ExpenseController::class.java)
    }
}