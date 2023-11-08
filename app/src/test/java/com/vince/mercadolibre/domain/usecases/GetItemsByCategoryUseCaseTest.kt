package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.models.PagingInfo
import com.vince.mercadolibre.domain.repositories.ItemRepository
import com.vince.mercadolibre.utils.ConstantsHelper.ITEMS_TO_REQUEST
import com.vince.mercadolibre.utils.MockableInit
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class GetItemsByCategoryUseCaseTest : MockableInit {

    @MockK
    lateinit var itemRepository: ItemRepository

    private val category = "MCO2344"

    private val itemsWithPaginationResult = ItemsWithPagination(
        PagingInfo(ITEMS_TO_REQUEST, ITEMS_TO_REQUEST),
        listOf(mockk<Item>()),
    )

    @Test
    fun `When useCase is invoked then it returns an ItemsWithPagination with values`() {
        coEvery {
            itemRepository.getItemsByCategory(category, ITEMS_TO_REQUEST)
        } returns CallResult.success(itemsWithPaginationResult)

        val useCase = GetItemsByCategoryUseCase(itemRepository)

        val result = runBlocking { useCase(category) }

        result shouldBeEqualTo CallResult.success(itemsWithPaginationResult)

        coVerify {
            itemRepository.getItemsByCategory(category, ITEMS_TO_REQUEST)
        }
    }
}
