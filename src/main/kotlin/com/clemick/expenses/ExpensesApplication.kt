package com.clemick.expenses

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ExpensesApplication

fun main(args: Array<String>) {
    SpringApplication.run(ExpensesApplication::class.java, *args)
}
