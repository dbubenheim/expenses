package com.clemick.expenses

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.MongoClient
import io.undertow.UndertowOptions
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.mongo.DefaultMongoTemplate
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.zalando.jackson.datatype.money.MoneyModule

@SpringBootApplication
class ExpensesApplication {

    @Value("\${http.version:'HTTP2'}")
    private val httpVersion: String? = null

    @Bean
    fun objectMapper() : ObjectMapper {
        return ObjectMapper()
                .registerModule(MoneyModule())
    }

    @Bean
    fun eventStore(client: MongoClient) : EventStorageEngine {
        return MongoEventStorageEngine(DefaultMongoTemplate(client))
    }

    @Bean
    fun embeddedServletContainerFactory(): UndertowServletWebServerFactory {
        val factory = UndertowServletWebServerFactory()
        if ("HTTP2" == httpVersion) {
            factory.addBuilderCustomizers(UndertowBuilderCustomizer {
                it.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                it.setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true)
            })
        }
        return factory
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ExpensesApplication::class.java, *args)
}

