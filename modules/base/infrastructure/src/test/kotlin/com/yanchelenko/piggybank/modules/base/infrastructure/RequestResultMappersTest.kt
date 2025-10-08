package com.yanchelenko.piggybank.modules.base.infrastructure

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RequestResultMappersTest {

    @Test
    fun `Result success maps to RequestResult Success`() {
        val rr = Result.success(Unit).toRequestResult()
        assertTrue(rr is RequestResult.Success)
    }

    @Test
    fun `Result failure maps to RequestResult Error with cause`() {
        val ex = IllegalStateException("boom")
        val rr = Result.failure<Unit>(ex).toRequestResult()
        assertTrue(rr is RequestResult.Error)
        assertEquals("boom", (rr as RequestResult.Error).error?.message)
    }

}
