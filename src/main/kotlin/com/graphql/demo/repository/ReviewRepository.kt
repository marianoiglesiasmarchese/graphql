package com.graphql.demo.repository

import com.graphql.demo.model.Review
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : MongoRepository<Review, String>, CustomReviewRepository

interface CustomReviewRepository {

    fun getReviews(snackId: String): List<Review>

}

class CustomReviewRepositoryImpl(
    val mongoTemplate: MongoTemplate
) : CustomReviewRepository {

    override fun getReviews(snackId: String): List<Review> {
        val query = Query()
        query.addCriteria(Criteria.where("snackId").`is`(snackId))
        return mongoTemplate.find(query, Review::class.java)
    }

}
