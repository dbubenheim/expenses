package com.clemick.expenses

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class TestController {

    @RequestMapping("test")
    fun test() : String {
        return "Hello World"
    }
}