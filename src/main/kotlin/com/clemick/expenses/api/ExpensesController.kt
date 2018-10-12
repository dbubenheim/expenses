package com.clemick.expenses.api

import com.clemick.expenses.aggregate.Account
import com.clemick.expenses.model.Owner
import com.clemick.expenses.query.AccountQuery
import com.clemick.expenses.view.AccountView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("expenses/api/v1")
class ExpensesController(
        private val commandGateway: CommandGateway,
        private val eventStore: EventStore,
        private val queryGateway: QueryGateway) {

    @PostMapping(produces = ["application/json"])
    @ResponseBody
    fun create(@RequestHeader("owner") owner: Owner, @RequestHeader("name") name: String) : CompletableFuture<String> {
        logger.info("create.....")
        return commandGateway.send(CreateCommand(UUID.randomUUID().toString(), name, owner))
    }

    @PutMapping("/put/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody name: String) {
        logger.info("update.....")
        commandGateway.send<Unit>(UpdateCommand(id, name))
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun get(@PathVariable id : String) : CompletableFuture<AccountView> {
        logger.info("get.....")
        //return queryGateway.query(AccountQuery(id), Account::class.java)
        return queryGateway.query(AccountQuery(id), AccountView::class.java)
        //return eventStore.readEvents(id).asSequence().map { it.payload }.toList()
    }

    @GetMapping("/wtf")
    @ResponseBody
    fun get() : String {
        return "WTF"
    }

    companion object {
        val logger : Logger = LoggerFactory.getLogger(ExpensesController::class.java)
    }
}