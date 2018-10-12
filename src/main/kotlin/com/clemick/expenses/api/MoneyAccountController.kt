package com.clemick.expenses.api

import com.clemick.expenses.command.CreateMoneyAccountCommand
import com.clemick.expenses.command.UpdateMoneyAccountCommand
import com.clemick.expenses.model.Owner
import com.clemick.expenses.query.MoneyAccountQuery
import com.clemick.expenses.view.MoneyAccountView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/account/api/v1")
class MoneyAccountController(
        private val commandGateway: CommandGateway,
        private val queryGateway: QueryGateway) {

    @PostMapping(produces = ["application/json"])
    @ResponseBody
    fun create(@RequestHeader("owner") owner: Owner, @RequestHeader("name") name: String) : CompletableFuture<String> {
        logger.info("create.....")
        return commandGateway.send(CreateMoneyAccountCommand(UUID.randomUUID().toString(), name, owner))
    }

    @PutMapping("/put/{id}")
    @ResponseBody
    fun update(@PathVariable("id") id: String, @RequestBody name: String) {
        logger.info("update.....")
        commandGateway.send<Unit>(UpdateMoneyAccountCommand(id, name))
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun get(@PathVariable id : String) : CompletableFuture<MoneyAccountView> {
        logger.info("get.....")
        return queryGateway.query(MoneyAccountQuery(id), MoneyAccountView::class.java)
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(MoneyAccountController::class.java)
    }
}