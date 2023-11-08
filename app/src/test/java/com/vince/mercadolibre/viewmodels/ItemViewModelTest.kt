package com.vince.mercadolibre.viewmodels

import androidx.lifecycle.Observer
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.models.PagingInfo
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsByQueryUseCase
import com.vince.mercadolibre.scenes.items.ItemsViewModel
import com.vince.mercadolibre.utils.ConstantsHelper.ITEMS_TO_REQUEST
import com.vince.mercadolibre.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.amshove.kluent.internal.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ItemViewModelTest : BaseViewModelTest() {
    @MockK
    private lateinit var getItemsByQueryUseCase: GetItemsByQueryUseCase

    @MockK
    private lateinit var getItemsByCategoryUseCase: GetItemsByCategoryUseCase

    @MockK
    private lateinit var elementsObserver: Observer<CallResult<List<Item>>>

    private lateinit var itemsViewModel: ItemsViewModel
    private val category = "MCO2343"
    private val query = "Celular"

    private val resultItems = listOf(mockk<Item>())

    private val itemsWithPaginationResult = ItemsWithPagination(
        PagingInfo(ITEMS_TO_REQUEST, ITEMS_TO_REQUEST),
        resultItems,
    )

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    override fun setup() {
        super.setup()

        every { elementsObserver.onChanged(any()) } just Runs

        coEvery {
            getItemsByCategoryUseCase(category)
        } returns CallResult.success(itemsWithPaginationResult)

        itemsViewModel = ItemsViewModel(getItemsByCategoryUseCase, getItemsByQueryUseCase)
    }

    @Test
    suspend fun getItemsByQuery_shouldReturnSuccessCallResult() {

        `when`(getItemsByQueryUseCase(query)).thenReturn(CallResult.Success(itemsWithPaginationResult))

        val callResult = itemsViewModel.getItemsByQuery(query).getOrAwaitValue()

        assertEquals(CallResult.Success(itemsWithPaginationResult), callResult)

        verify {

        }
    }
}