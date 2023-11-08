package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.models.PagingInfo
import com.vince.mercadolibre.domain.repositories.ItemRepository
import com.vince.mercadolibre.utils.MockableInit
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class GetDetailedItemUseCaseTest : MockableInit {

    @MockK
    lateinit var itemRepository: ItemRepository

    private val itemsToRequest = 20
    private val query = "celular"

    private val itemsWithPaginationResult = ItemsWithPagination(
        PagingInfo(20, 20),
        listOf(mockk<Item>()),
    )

    @Test
    fun `When useCase is invoked then it returns an ItemsWithPagination with values`() {
        coEvery {
            itemRepository.getItemsByQuery(query, itemsToRequest)
        } returns CallResult.success(itemsWithPaginationResult)

        val useCase = GetItemsByQueryUseCase(itemRepository)

        val result = runBlocking { useCase(query) }

        result shouldBeEqualTo CallResult.success(itemsWithPaginationResult)

        coVerify {
            itemRepository.getItemsByQuery(query, itemsToRequest)
        }
    }
}
