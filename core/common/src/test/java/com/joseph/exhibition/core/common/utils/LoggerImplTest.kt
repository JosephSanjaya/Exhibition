package com.joseph.exhibition.core.common.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class LoggerImplTest : FunSpec({

    // Create a mock instance of Firebase Crashlytics
    val crashlytics = mockk<FirebaseCrashlytics>(relaxed = true)

    // Create an instance of LoggerImpl with the mock crashlytics
    val logger: Logger = LoggerImpl(crashlytics)

    test("logIssueAsNonFatal should log a throwable and its params as non-fatal error") {
        // Arrange
        val throwable = Throwable("Test error")
        val params = arrayOf("key1" to "value1", "key2" to 2)
        // Capture the logged messages and recorded exceptions with slots
        val logSlot = slot<String>()
        val exceptionSlot = slot<Throwable>()
        every { crashlytics.log(capture(logSlot)) } answers { nothing }
        every { crashlytics.recordException(capture(exceptionSlot)) } answers { nothing }

        // Act
        logger.logIssueAsNonFatal(throwable, *params)

        // Assert
        // Assert that the tag and message are logged to crashlytics
        logSlot.captured shouldBe "Message: Test error"
        // Assert that the throwable is recorded as non-fatal error
        exceptionSlot.captured shouldBe throwable
    }

    test("logIssueAsNonFatal should log a message and its params as non-fatal error") {
        // Arrange
        val msg = "Test message"
        val params = arrayOf("key3" to true, "key4" to 4.0)
        // Capture the logged messages and recorded exceptions with slots
        val logSlot = slot<String>()
        val exceptionSlot = slot<Throwable>()
        every { crashlytics.log(capture(logSlot)) } answers { nothing }
        every { crashlytics.recordException(capture(exceptionSlot)) } answers { nothing }

        // Act
        logger.logIssueAsNonFatal(msg, *params)

        // Assert
        // Assert that the tag and message are logged to crashlytics
        logSlot.captured shouldBe "Message: Test message"
        // Assert that the message is recorded as non-fatal error with a throwable wrapper
        exceptionSlot.captured.message shouldBe msg
    }
})
