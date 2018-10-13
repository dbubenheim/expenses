package com.clemick.expenses

import com.clemick.expenses.api.UpdateMoneyAccountRequest
import com.clemick.expenses.api.request.CreateMoneyAccountRequest
import com.clemick.expenses.model.Owner
import com.clemick.expenses.view.MoneyAccountView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyAccountControllerTest {

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

        assertThat(moneyAccountId).isNotNull()
        assertThat(moneyAccountId).matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}\$")
    }

    @Test
    fun testGet() {

        val url = "http://localhost:$port/account/api/v1"
        val payload = CreateMoneyAccountRequest("Foo", Owner("Bla"))
        val responseEntity = restTemplate.postForEntity(url, payload, String::class.java)
        assertThat(HttpStatus.CREATED).isEqualTo(responseEntity.statusCode)
        val moneyAccountId = responseEntity.body

        val entity = restTemplate.getForEntity("$url/$moneyAccountId", MoneyAccountView::class.java)
        assertThat(HttpStatus.OK).isEqualTo(entity.statusCode)

        assertThat(entity.body!!.name).isEqualTo("Foo")
        assertThat(entity.body!!.id).isEqualTo(moneyAccountId)
        assertThat(entity.body!!.owner).isEqualTo(Owner("Bla"))
    }

    @Test
    fun testPut() {

        val url = "http://localhost:$port/account/api/v1"
        val postPayload = CreateMoneyAccountRequest("Foo", Owner("Bla"))
        val responseEntity = restTemplate.postForEntity(url, postPayload, String::class.java)
        val moneyAccountId = responseEntity.body
        val postEntity = restTemplate.getForEntity("$url/$moneyAccountId", MoneyAccountView::class.java)
        assertThat(postEntity.statusCode).isEqualTo(HttpStatus.OK)

        val putPayload = UpdateMoneyAccountRequest("Bar", Owner("Updated"))

        //restTemplate.put("$url/$moneyAccountId", putPayload, MoneyAccountView::class.java)
        val putEntity = restTemplate.exchange("$url/$moneyAccountId", HttpMethod.PUT, HttpEntity(putPayload), String::class.java)
        assertThat(putEntity.statusCode).isEqualTo(HttpStatus.OK)

        val entity = restTemplate.getForEntity("$url/$moneyAccountId", MoneyAccountView::class.java)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(entity.body!!.name).isEqualTo("Bar")
        assertThat(entity.body!!.id).isEqualTo(moneyAccountId)
        assertThat(entity.body!!.owner).isEqualTo(Owner("Updated"))
    }
}