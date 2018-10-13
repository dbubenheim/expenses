package com.clemick.expenses.repository

/*
@Repository
@Profile("mongodb")
internal class MongoDbCustomerRepository @Autowired constructor(private val operations: MongoOperations) : AccountRepository {

    override fun findOne(id : String): Account {

        val query = query(where("id"))
        return operations.findOne(query, Account::class.java)!!
    }

    override fun save(account: Account): Account {

        operations.save(account)
        return account
    }

    override fun findByName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun findByEmailAddress(emailAddress: EmailAddress): Customer? {
        val query = query(where("emailAddress").`is`(emailAddress))
        return operations.findOne(query, Customer::class.java!!)
    }
}*/