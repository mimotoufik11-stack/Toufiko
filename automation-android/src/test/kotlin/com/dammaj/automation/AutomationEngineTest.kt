package com.dammaj.automation

import org.junit.Before
import org.junit.Test

class AutomationEngineTest {
    private lateinit var engine: AutomationEngine

    @Before
    fun setup() {
        engine = AutomationEngine()
    }

    @Test
    fun `initialize should not throw exception`() {
        engine.initialize()
    }

    @Test
    fun `executeTask should not throw exception`() {
        engine.initialize()
        engine.executeTask("test-task-id")
    }

    @Test
    fun `shutdown should not throw exception`() {
        engine.initialize()
        engine.shutdown()
    }
}
