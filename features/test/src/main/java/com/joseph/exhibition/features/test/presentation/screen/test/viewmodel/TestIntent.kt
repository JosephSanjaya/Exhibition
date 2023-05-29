package com.joseph.exhibition.features.test.presentation.screen.test.viewmodel

/**
 * A sealed class that represents the possible intents for the test logic.
 */
sealed class TestIntent {
    // The intent to perform the first test.
    object DoTest1 : TestIntent()

    // The intent to perform the second test.
    object DoTest2 : TestIntent()
}
