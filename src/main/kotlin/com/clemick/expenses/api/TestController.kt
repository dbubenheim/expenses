package com.clemick.expenses.api

import com.clemick.expenses.aggregate.Expense
import com.clemick.expenses.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class TestController {

    @Autowired
    lateinit var expensesRepository: ExpensesRepository

    @GetMapping
    @RequestMapping("test")
    fun test() : String {
        return "Hello World"
    }

    @GetMapping
    @RequestMapping("expenses")
    fun getAllExpenses() : List<Expense>
    {
        return this.expensesRepository.findAll()
    }
}