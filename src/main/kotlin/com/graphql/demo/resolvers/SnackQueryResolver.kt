package com.graphql.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.graphql.demo.model.Review
import com.graphql.demo.model.Snack
import com.graphql.demo.repository.ReviewRepository
import com.graphql.demo.repository.SnackRepository
import org.springframework.stereotype.Component

/**
 * Spring matches this class name as resolver of the snack query declared at snack.graphqls
 */
@Component
class SnackQueryResolver(
    private val snackRepository: SnackRepository,
    private val reviewRepository: ReviewRepository
) : GraphQLQueryResolver {

    /**
     *  Notice that the field name corresponds to the function name from snack.graphqls
     */
    fun snacks(): List<Snack> {
        val list = snackRepository.findAll()
        for (item in list) {
            item.reviews = reviewRepository.getReviews(snackId = item.id)
        }
        return list
    }

}