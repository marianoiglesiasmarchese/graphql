package com.graphql.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.graphql.demo.model.Review
import com.graphql.demo.repository.ReviewRepository
import org.springframework.stereotype.Component

/**
 * Spring matches this class name as resolver of the review query declared at snack.graphqls
 */
@Component
class ReviewQueryResolver(
    private val reviewRepository: ReviewRepository
) : GraphQLQueryResolver {

    fun reviews(snackId: String): List<Review> {
        return reviewRepository.getReviews(snackId)
    }

}