package com.clemick.expenses

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.zalando.jackson.datatype.money.MoneyModule

@SpringBootApplication
class ExpensesApplication {
    @Bean
    fun objectMapper() : ObjectMapper {
        return ObjectMapper()
                .registerModule(MoneyModule())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ExpensesApplication::class.java, *args)
}