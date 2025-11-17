package com.dammaj.core.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {
    @Test
    fun `success result should be success`() {
        val result = Result.Success("test")
        assertTrue(result.isSuccess)
        assertFalse(result.isError)
        assertFalse(result.isLoading)
    }

    @Test
    fun `error result should be error`() {
        val result = Result.Error(Exception("test"))
        assertFalse(result.isSuccess)
        assertTrue(result.isError)
        assertFalse(result.isLoading)
    }

    @Test
    fun `loading result should be loading`() {
        val result = Result.Loading
        assertFalse(result.isSuccess)
        assertFalse(result.isError)
        assertTrue(result.isLoading)
    }

    @Test
    fun `success result should contain data`() {
        val data = "test data"
        val result = Result.Success(data)
        assertEquals(data, result.data)
    }
}
