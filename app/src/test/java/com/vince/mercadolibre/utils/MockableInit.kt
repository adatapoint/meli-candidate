package com.vince.mercadolibre.utils

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

interface MockableInit {

    val resultUnit: Result<Unit>
        get() = Result.success(Unit)

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
