package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.GetActiveCartTotalsUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveCartTotalsUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : GetActiveCartTotalsUseCase {

    override fun invoke(): Flow<CartTotals> = cartRepository.observeActiveCartTotals()
}
