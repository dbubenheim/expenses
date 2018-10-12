package com.clemick.expenses

import com.clemick.expenses.api.request.CreateMoneyAccountRequest
import com.clemick.expenses.model.Owner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun testPost() {

        val url = "http://localhost:$port/account/api/v1"
        val payload = CreateMoneyAccountRequest("Foo", Owner("Bla"))
        val responseEntity = restTemplate.postForEntity(url, payload, String::class.java)
        val moneyAccountId = responseEntity.body
        assertThat(HttpStatus.CREATED).isEqualTo(responseEntity.statusCode)
        //assertThat("Foo").isEqualTo(moneyAccountView?.name)
        assertThat(moneyAccountId).isNotNull()
        assertThat(moneyAccountId).matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}\$")
    }
}