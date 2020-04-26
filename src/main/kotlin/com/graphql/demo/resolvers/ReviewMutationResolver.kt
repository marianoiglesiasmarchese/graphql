package com.graphql.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.graphql.demo.model.Review
import com.graphql.demo.repository.ReviewRepository
import org.springframework.stereotype.Component

/**
 * Spring matches this class name as resolver of the review mutations declared at snack.graphqls
 */
@Component
class ReviewMutationResolver(
    private val reviewRepository: ReviewRepository
) : GraphQLMutationResolver {

    fun newReview(snackId: String, rating: Int, text: String): Review {
        val review = Review(snackId, rating, text)
        reviewRepository.save(review)
        return review
    }

}