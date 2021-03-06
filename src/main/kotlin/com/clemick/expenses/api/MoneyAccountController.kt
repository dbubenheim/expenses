package com.clemick.expenses.api

import com.clemick.expenses.api.request.CreateMoneyAccountRequest
import com.clemick.expenses.command.CreateMoneyAccountCommand
import com.clemick.expenses.command.DeleteMoneyAccountCommand
import com.clemick.expenses.command.UpdateMoneyAccountCommand
import com.clemick.expenses.query.MoneyAccountQuery
import com.clemick.expenses.view.MoneyAccountView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/account/api/v1")
class MoneyAccountController(
        private val commandGateway: CommandGateway,
        private val queryGateway: QueryGateway) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun get(@PathVariable id : String) : CompletableFuture<MoneyAccountView> {
        logger.info("get.....")
        return queryGateway.query(MoneyAccountQuery(id), MoneyAccountView::class.java)
    }

    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun create(@RequestBody request: CreateMoneyAccountRequest) : CompletableFuture<String> {
        logger.info("create.....")
        return commandGateway.send<String>(CreateMoneyAccountCommand(UUID.randomUUID().toString(), request.name, request.owner))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun update(@PathVariable("id") id: String, @RequestBody request: UpdateMoneyAccountRequest): CompletableFuture<String> {
        logger.info("update.....")
        return commandGateway.send<String>(UpdateMoneyAccountCommand(id, request.name, request.owner))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id : String): CompletableFuture<String> {
        logger.info("delete.....")
        return commandGateway.send<String>(DeleteMoneyAccountCommand(id))
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MoneyAccountController::class.java)
    }
}