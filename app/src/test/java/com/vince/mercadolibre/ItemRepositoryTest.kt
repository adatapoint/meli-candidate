package com.vince.mercadolibre

import com.vince.mercadolibre.data.repositories.ItemRepositoryImpl
import com.vince.mercadolibre.data.services.ItemService
import com.vince.mercadolibre.utils.MockableInit
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class ItemRepositoryTest : MockableInit {

    @MockK
    lateinit var itemService: ItemService

    private lateinit var repository: ItemRepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        repository = ItemRepositoryImpl(itemService)
    }

    @Test
    fun `When getItemsByQuery is called then it returns a list of Items`() {
//        val query = "motorola"
//        val limit = 20
//        val itemsList = listOf(Item("asdf", "Lavadora", "url", "COP", 3234, "New"))
//        val pagingInfo = PagingInfo(20, 20)
//        val itemsWithPagination = ItemsWithPagination(pagingInfo, itemsList)
//        val call = mockk<Call<ItemsWithPagination<Item>>>()
//        val responseData = mockk<Response<ItemsWithPagination<Item>>>(relaxed = true)
//        val slot = CapturingSlot<Callback<ItemsWithPagination<Item>>>()
//
//        coEvery {
//            call.enqueue(capture(slot))
//        } answers {
//            slot.captured.onResponse(call, responseData)
//        }
//
//
//        coEvery {
//            itemService.getItemsByQuery(query, limit)
//        } returns mockk {
//            every { isSuccessful } returns true
//            every { body() } returns itemsWithPagination
//        }
//
//        coEvery { call.execute() } answers {
//            responseData
//        }
//
//        coVerify {
//            itemService.getItemsByQuery(query, limit)
//            call.execute()
//        }
//
//        runBlocking {
//            repository.getItemsByQuery(query, limit)
//        }
//        coEvery {
//            call.enqueue(capture(slot))
//        } answers {
//            slot.captured.onResponse(call, responseData)
//        }
//
//        coEvery {
//            itemService.getItemsByQuery(query, limit)
//        } returns call
//
//        coEvery { call.execute() } answers {
//            responseData
//        }
//
//        // Invocar el método bajo prueba
//        runBlocking {
//            repository.getItemsByQuery(query, limit)
//        }
//
//        // Verificar las interacciones y resultados
//        coVerify {
//            itemService.getItemsByQuery(query, limit)
//            call.execute()
//        }
//
//        coEvery {
//            itemService.getItemsByQuery(query, limit)
//        } returns mockk {
//            every { isSuccessful } returns true
//            every { body() } returns itemsWithPagination
//        }
    }
}
