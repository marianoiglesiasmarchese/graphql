package com.graphql.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.graphql.demo.model.Snack
import com.graphql.demo.repository.SnackRepository
import org.springframework.stereotype.Component
import java.util.*

/**
 * Spring matches this class name as resolver of the snack mutations declared at snack.graphqls
 */
@Component
class SnackMutationResolver(
    private val snackRepository: SnackRepository
) : GraphQLMutationResolver {

    fun newSnack(name: String, amount: Float): Snack {
        val snack = Snack(name, amount)
        snack.id = UUID.randomUUID().toString()
        snackRepository.save(snack)
        return snack
    }

    fun deleteSnack(id: String): Boolean {
        snackRepository.deleteById(id)
        return true
    }

    fun updateSnack(id: String, amount: Float): Snack {
        val snack = snackRepository.findById(id)
        snack.ifPresent {
            it.amount = amount
            snackRepository.save(it)
        }
        return snack.get()
    }
}